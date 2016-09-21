package ch00

import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

final case class Message(sender: String, content: String, id: Long = 0L)

final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def sender = column[String]("sender")
  def content = column[String]("content")

  def * = (sender, content, id) <> (Message.tupled, Message.unapply)
}

object Main {
  val messages = TableQuery[MessageTable]

  // Helper method for creating test data
  def testData = Seq(
    Message("Dave", "Hello, HAL. Do you read me, HAL?"),
    Message("HAL",  "Affirmative, Dave. I read you."),
    Message("Dave", "Open the pod bay doors, HAL."),
    Message("HAL",  "I'm sorry, Dave. I'm afraid I can't do that.")
  )

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

  val db = Database.forConfig("chapter02")
  // Helper method for running a query in this example file:
  def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 5000 milliseconds)
}