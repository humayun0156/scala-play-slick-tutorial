package ch02

import slick.driver.H2Driver.api._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object MainChapter02 {

  final case class Message(sender: String, content: String, id: Long = 0L)

  // Helper method for creating test data
  def freshTestData = Seq(
    Message("Dave", "Hello, HAL. Do you read me, HAL?"),
    Message("HAL",  "Affirmative, Dave. I read you."),
    Message("Dave", "Open the pod bay doors, HAL."),
    Message("HAL",  "I'm sorry, Dave. I'm afraid I can't do that."),
    Message("humayun",  "kabir")
  )

  // Schema for the "message" table
  final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {
    def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def sender  = column[String]("sender")
    def content = column[String]("content")

    def * = (sender, content, id) <> (Message.tupled, Message.unapply)
  }

  case class TextOnly(id: Long, content: String)

  def main(args: Array[String]): Unit = {
    val messages = TableQuery[MessageTable]
    val halSays = messages.filter(_.sender === "HAL")

    // Create an in-memory H2 database;
    val db = Database.forConfig("H2")

    // Helper method for running a query in this example file:
    def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)

    try {
      // Create the "messages" table:
      println("Creating database table")
      exec(  messages.schema.create  )

      // Create and insert the test data:
      println("\nInserting test data")
      exec(  messages ++= freshTestData  )

      // Run the test query and print the results:
      println("\nSelecting all message sender names:")
      exec(  messages.map(_.sender).result  ) foreach println

      println("\nSelecting only 'sorry' messages:")
      println(
        exec(
          messages.map(_.content).filter(_ like "%sorry%").result
        )
      )

      println("=====")
      val contentQuery =
        messages.map(t => (t.id, t.content) <> (TextOnly.tupled, TextOnly.unapply))

      exec(contentQuery.result) foreach println

      println("=====")
      val containsBay = for {
        m <- messages
        if m.content like "%bay%"
      } yield m
      val bayMentioned: DBIO[Boolean] = containsBay.exists.result
      println(exec(bayMentioned))

      println("===== Stream")
      db.stream(messages.result).foreach(println)

      println("===== Column Expressions")
      println(messages.filter(_.sender === "Dave").result.statements)
      println(messages.filter(_.sender =!= "Dave").result.statements)
      println(messages.filter(_.sender < "HAL").result.statements)
      println(messages.filter(m => m.sender >= m.content).result.statements)
      println("===== String Column Expressions")
      println(messages.map(m => m.sender ++ "> " ++ m.content).result.statements)
      exec(messages.map(m => m.sender ++ "> " ++ m.content).result) foreach println
      println(messages.map(_.content like "%read%").result.statements)
      exec(messages.map(_.content like "%read%").result) foreach println

      println("===== ")
      println(messages.filter(x => x.sender === "humayun" && x.content === "kabir" ).result.statements)
      exec(messages.filter(x => x.sender === "humayun" && x.content === "kabir" ).result) foreach println
      println(messages.filter(_.id === Option(123L)).result.statements)
      //any optional arguments must be strictly of type Option, not Some or None:
      //println(messages.filter(_.id === Some(123L)).result.statements) /*NOT COMPILE*/
      //println(messages.filter(_.id === None(123L)).result.statements) /*NOT COMPILE*/

      println("==== Sort, take, drop -> Order by, Limit, Offset")
      exec(messages.sortBy(_.sender).result) foreach println
      println("---: " + messages.sortBy(m => (m.sender, m.content)).result.statements)
      exec(messages.sortBy(m => (m.sender, m.content)).result) foreach println
      // we want to show only the first five rows:
      println("---: " + messages.sortBy(_.sender).take(5).result.statements)
      //If we are presenting information in pages, we’d need a way to show the next page (rows 6 to 10)
      println("---: " + messages.sortBy(_.sender).drop(5).take(5).result.statements)

      println("===== Exercise")
      println(messages.length.result.statements)
      println(halSays.length.result.statements)
      val result = exec(halSays.length.result)
      println("Count: " + result)

      //2
      val query = for {
        message <- messages if message.id === 1L
      } yield message
      println(exec(query.result))
      //3
      println(exec(messages.filter(_.id === 1L).result))
      //exists
      val q = messages.filter(_.sender === "HAL").exists
      println(s"The query is: ${q.result.statements}")
      println(s"The result is: ${exec(q.result)}")

      println("===== selection a column")//
      val q1 = messages.map(_.sender)
      val q2 = for { message <- messages } yield  message.content
      println(s"The Query is: ${q1.result.statements}")
      println(s"The result is: ${exec(q1.result)}")

      println("===== Get the first Result")
      val q3 = messages.filter(_.sender === "HAL").map(_.content).result.head
      println(s"The Query is: ${q3.statements}")
      println(s"The result is: ${exec(q3)}")

      println("===== Then the rest")
      val q4 = messages.filter(_.sender === "HAL").drop(1).take(1).result
      println(s"Query: ${q4.statements}")
      println(s"Result: ${exec(q4)}")

      println("===== The start of something")
      val q5 = messages.filter(_.content startsWith "Open").result
      println(s"Query: ${q5.statements}")
      println(s"Result: ${exec(q5)}")

      println("===== Liking")
      val q6 = messages.filter(_.content.toLowerCase like "%do%").result
      println(s"Query: ${q6.statements}")
      println(s"Result: ${exec(q6)}")

      println("===== Client/Server Side")
      val q7 = messages.map(_.content + "!").result
      val q8 = messages.map(_.content ++ "!").result
      println(s"Query: ${q7.statements}\n${q8.statements}")
      println(s"Result: ${exec(q7)}\n ${exec(q8)}")


    } finally db.close()
  }
}
