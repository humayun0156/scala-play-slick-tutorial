package ch03

import slick.driver.H2Driver.api._
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author humayun
  */
object MainChapter03 {

  final case class Message(sender: String, content: String, id: Long = 0L)

  // Helper method for creating test data
  def testData = Seq(
    Message("Dave", "Hello, HAL. Do you read me, HAL?"),
    Message("HAL",  "Affirmative, Dave. I read you."),
    Message("Dave", "Open the pod bay doors, HAL."),
    Message("HAL",  "I'm sorry, Dave. I'm afraid I can't do that.")
  )

  final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def sender = column[String]("sender")
    def content = column[String]("content")

    def * = (sender, content, id) <> (Message.tupled, Message.unapply)
  }

  def main(args: Array[String]): Unit = {
    val messages = TableQuery[MessageTable]
    val halSays = messages.filter(_.sender === "HAL")

    val db = Database.forConfig("chapter02")

    // Helper method for running a query in this example file:
    def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 5000 milliseconds)

    def populate: DBIOAction[Option[Int], NoStream, Effect.All] = {
      for {
        //Drop table if it already exists, then create the table:
        _ <- messages.schema.drop.asTry andThen messages.schema.create
        // Add some data:
        count <- messages ++= testData
      } yield count
    }

    // Utility to print out what is in the database:
    def printCurrentDatabaseStage() = {
      println("\n===== State of the database:")
      exec(messages.result.map(_.foreach(println)))
    }

    try {
      exec(populate)
      // -- INSERTS --

      // Insert one, returning the ID:
      val id = exec((messages returning messages.map(_.id)) += Message("HAL", "I'm back"))
      println(s"The ID inserted was: $id")

      // -- DELTES --

      // Delete message from HAL:
      println("\nDeleting messages from HAL")
      val rowsDeleted = exec(messages.filter(_.sender === "HAL").delete)
      println(s"Rows Deleted: $rowsDeleted")

      // Repopulate the database
      exec( messages ++= testData.filter(_.sender == "HAL"))
      printCurrentDatabaseStage()

      // -- UPDATES --
      // Update HAL's name
      val rows = exec(messages.filter(_.sender === "HAL").map(_.sender).update("HAL 9000"))
      printCurrentDatabaseStage()


      println("==== 3.1 Insert")
      // ++= for muliple rows & += for single row
      val action = messages += Message("HAL", "No, Seriously, Dave, I can't let you in.")
      exec(action)
      printCurrentDatabaseStage()

      val forceInsertAction = messages forceInsert Message("HAL", "I'm a computer", 1000L)
      println(forceInsertAction.statements.head)
      exec(forceInsertAction)
      println(
        exec(messages.filter(_.id === 1000L).result).head
      )

      val insert: DBIO[Long] = messages returning messages.map(_.id) += Message("Dave", "Point taken")
      println(exec(insert))

      lazy val messageReturningId = messages returning messages.map(_.id)
      println(
        exec(messageReturningId += Message("HAL", "I don't know"))
      )

      // multiple row insert
      val testMessages = Seq(
        Message("Dave", "Hello, HAL. Do you read me, HAL?"),
        Message("HAL", "Affirmative, Dave. I read you."),
        Message("Dave", "Open the pod bay doors, HAL."),
        Message("HAL", "I'm sorry, Dave. I'm afraid I can't do that.")
      )
      println(exec(messages ++= testMessages))

    } finally db.close()
  }
}
