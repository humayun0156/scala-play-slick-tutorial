package ch01

import slick.driver.H2Driver.api._

import scala.concurrent.Await
import scala.concurrent.duration._

object MainChapter01 {
  final case class Message(sender: String, content: String, id: Long = 0L)

  // Helper method for creating test data
  def freshTestData = Seq(
    Message("Dave", "Hello, HAL. Do you read me, HAL?"),
    Message("HAL",  "Affirmative, Dave. I read you."),
    Message("Dave", "Open the pod bay doors, HAL."),
    Message("HAL",  "I'm sorry, Dave. I'm afraid I can't do that.")
  )

  // Schema for the "message" table
  final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {
    def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def sender  = column[String]("sender")
    def content = column[String]("content")

    def * = (sender, content, id) <> (Message.tupled, Message.unapply)
  }

  def main(args: Array[String]): Unit = {
    // Base query for querying the messages table:
    lazy val messages = TableQuery[MessageTable]

    // An example query that selects a subset of messages:
    val halSays = messages.filter(_.sender === "HAL")

    // Create an in-memory H2 database;
    val db = Database.forConfig("chapter01")

    // Helper method for running a query in this example file:
    def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds )

    // Create the "messages" table:
    println("Creating database table")
    exec(messages.schema.create)

    // Create and insert the test data:
    println("\nInserting test data")
    exec(messages ++= freshTestData)

    // Run the test query and print the results:
    println("\nSelecting all messages:")
    exec(messages.result) foreach { println }

    println("\nSelecting only messages from HAL:")
    exec(halSays.result) foreach { println }

    val halSays2 = for {
      message <- messages if message.sender === "HAL"
    } yield message

    println("\nSelecting only messages from HAL 2:")
    exec(halSays2.result).foreach(println)
    println("======================================")
    val actions: DBIO[Seq[Message]] = (
      messages.schema.create andThen
      (messages ++= freshTestData) andThen
      halSays.result
      )

    val actions_1 = (
      messages.schema.create >>
        (messages ++= freshTestData) >>
      halSays2.result
      )

    //  exec(actions)

    println("======================================")
    println(messages.schema.createStatements.mkString)
    println(messages.result.statements.mkString)
    println(messages.filter(_.sender === "HAL").result.statements.mkString)
    println("============== Ex 1 ==================")
    exec(messages += Message("Dave", "What if I say 'Pretty please'?"))
    exec(messages.result).foreach(println)
    println("============== Ex 2 ==================")
    exec(messages.filter(_.sender === "Dave").result) foreach(println)
  }
}
