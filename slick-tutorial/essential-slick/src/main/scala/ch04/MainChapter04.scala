package ch04

import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import ch00.Main._
import ch00._
import scala.util.Try

object MainChapter04 {
  def main(args: Array[String]): Unit = {
    try {
      exec(populate)
      // Map
      val textOption: DBIO[Option[String]] =  messages.map(_.content).result.headOption
      val encrypted: DBIO[Option[String]] =
        textOption.map(maybeText => maybeText.map(_.reverse))
      println(  exec(encrypted)  )

      println("==== andThen / >>")
      val reset: DBIO[Int] = messages.delete andThen messages.size.result
      println(  exec(reset) )
      exec(populate)

      println("==== DBIO.Seq")
      val result_1: DBIO[Unit] = DBIO.seq(messages.delete, messages.size.result)
      println(  exec(result_1)  )
      exec(populate)

      println("==== flatMap")
      val delete: DBIO[Int] = messages.delete
      def insert(count: Int) = messages += Message("NOBODY", s"I removed ${count} messages")

      //val resMessageAction: DBIO[Int] = delete.flatMap { count => insert(count) }
      val resMessageAction: DBIO[Int] =
        delete.flatMap {
          case 0 => DBIO.successful(0)
          case n => insert(n)
        }
      exec(resMessageAction)
      printCurrentDatabaseState()
      exec(populate)

      println("==== DBIO.sequence")

      def reverse(msg: Message): DBIO[Int] =
        messages.filter(_.id === msg.id).map(_.content).update(msg.content.reverse)
      val updates: DBIO[Seq[Int]] =
        messages.result.flatMap(msgs => DBIO.sequence(msgs.map(reverse)))


      println("==== Fold")
      val report1: DBIO[Int] = DBIO.successful(42)
      val report2: DBIO[Int] = DBIO.successful(3)
      val report3: DBIO[Int] = DBIO.successful(5)
      val reports: List[DBIO[Int]] = report1 :: report2 :: report3 :: Nil

      val summary: DBIO[Int] = DBIO.fold(reports, 0) {
        (a, b) => a + b
      }
      println("Summary of all reports via fold:")
      println(exec(summary))

      println("==== zip")
      val countAndHal: DBIO[(Int, Seq[Message])] =
        messages.size.result zip messages.filter(_.sender === "HAL").result
      println(  exec(countAndHal)  )

      println("==== Transaction")
      def updateContent(id: Long)=
        messages.filter(_.id === id).map(_.content)
      exec {
        (
          updateContent(2L).update("Wanna come in?") andThen
          updateContent(3L).update("Pretty please!!") andThen
          updateContent(4L).update("Opening now")
        ).transactionally
      }
      printCurrentDatabaseState()

      val willRollback = (
        (messages += Message("HAL",  "Daisy, Daisy..."))                   >>
        (messages += Message("Dave", "Please, anything but your singing")) >>
          DBIO.failed(new Exception("agggh my ears"))                       >>
          (messages += Message("HAL", "Give me your answer do"))
        ).transactionally

      println("\nResult from rolling back:")
      println(exec(willRollback.asTry))
      printCurrentDatabaseState()

      println("=========== Exercise ============")
      val drop: DBIO[Unit] = messages.schema.drop
      val create: DBIO[Unit] = messages.schema.create
      val populate1: DBIO[Option[Int]] = messages ++= testData
      exec(drop andThen create andThen populate1)

      println("===== Ex.2")
      //Create a method that will insert a message, but if it is the first message in the
      // database, automtically insert the message “First!” before it
      def insert1(m: Message): DBIO[Int] = {
        messages.size.result.flatMap {
          case 0 => (messages += Message(m.sender, "First!")) andThen (messages += m)
          case n => (messages += m)
        }
      }
      //delete all the message
      println(s"Deleted ${exec(messages.delete)} messages")
      exec {
        insert1(Message("Me", "Hello !!!"))
      }
      printCurrentDatabaseState()
      exec(messages.delete andThen populate)

      println("==== Ex. 3")
      //a method that guarantees that an action will return only one result
      def onlyOne[T](action: DBIO[Seq[T]]): DBIO[T] = action.flatMap {
        xs => xs match {
          case x +: Nil => DBIO.successful(x)
          case ys       => DBIO.failed(
            new RuntimeException(s"Expected 1 result, not ${ys.length}")
          )
        }
      }
      def exactlyOne[T](action: DBIO[Seq[T]]): DBIO[Try[T]] = onlyOne(action).asTry

      val happy = messages.filter(_.content like "%sorry%").result
      println(
        exec(exactlyOne(happy))
      )
      val boom = messages.filter(_.content like "%I%").result
      println(
        exec(exactlyOne(boom))
      )

      println("===== Ex. 5")
      //println(exec(messages.filter(_.id === 34343L).result))

    } finally db.close()

  }
}
