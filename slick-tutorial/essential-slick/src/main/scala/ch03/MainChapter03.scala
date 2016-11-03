package ch03

import slick.driver.H2Driver.api._
import ch00.Main._
import ch00._

import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble
/**
  * @author humayun
  */
object MainChapter03 {

  def main(args: Array[String]): Unit = {

    val halSays = messages.filter(_.sender === "HAL")

    try {
      exec(populate)
      // -- INSERTS --

      // Insert one, returning the ID:
      val id = exec((messages returning messages.map(_.id)) += Message("HAL", "I'm back"))
      val iid = Await.result(db.run {
        (messages returning messages.map(_.id)) += Message("Humayun", "I'm Here......")
      }, 5000 millisecond)
      println("------>" + iid)
      val iidd = exec(messages.filter(_.id === 5L).result.headOption) match {
        case Some(x) => x.sender
        case None => println("none")
      }
        /*messages returning messages.filter(x => x.id === 5).result.headOption
      }, 5000 millisecond)*/
      println("++++++++++++:>" + iidd)
      println(s"The ID inserted was: $id")

      // -- DELTES --

      // Delete message from HAL:
      println("\nDeleting messages from HAL")
      val rowsDeleted = exec(messages.filter(_.sender === "HAL").delete)
      println(s"Rows Deleted: $rowsDeleted")

      // Repopulate the database
      exec( messages ++= testData.filter(_.sender == "HAL"))
      printCurrentDatabaseState()

      // -- UPDATES --
      // Update HAL's name
      val updateQuery = messages.filter(_.sender === "HAL").map(_.sender).update("HAL 9000")
      println(s"Update Statement:\n ${updateQuery.statements}")
      val rows = exec(updateQuery)
      printCurrentDatabaseState()

      // Update multiple fields
      val upQuery = messages
        .filter(_.id === 4L)
        .map(message => (message.sender, message.content))
      val upAction = upQuery.update(("HAL 9000", "Sure Dave, Come right now!"))
      exec(upAction)
      exec(messages.filter(_.sender === "HAL 9000").result).foreach( println )


      println("==== 3.1 Insert")
      // ++= for muliple rows & += for single row
      val action = messages += Message("HAL", "No, Seriously, Dave, I can't let you in.")
      exec(action)
      printCurrentDatabaseState()

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

      // Sometimes you need more flexibility, including inserting data based on another query
      // insertExpression.forceInsertQuery(selectExpression)
      val data = Query(("Stanley", "Cut!"))
      val exists =
        messages
          .filter(m => m.sender === "Stanley" && m.content === "Cut!")
          .exists // exists: slick.lifted.Rep[Boolean] = Rep(Apply Function exists)
      val selectExpression = data.filterNot(_ => exists)
      val action1 =
        messages.map(m => m.sender -> m.content).forceInsertQuery(selectExpression)
      println(exec(action1))
      println(exec(action1))

      println("==== Delete")
      val removeHal: DBIO[Int] = messages.filter(_.sender === "HAL").delete
      println(messages.filter(_.sender === "HAL").delete.statements.head)
      println( exec(removeHal) )


      println("===== Exercise")
      //Write a query to delete messages that contain “sorry”.
      val q1 = messages.filter(_.content like "%sorry%").delete
      println(exec(q1))

      // Delete HALs first two messages
      val selectiveMemory =
      messages.filter{
        _.id in messages.
          filter(_.sender === "HAL").sortBy(_.id asc).map(_.id).take(2)
      }.delete
      println(selectiveMemory.statements)
      println( exec(selectiveMemory) )

    } finally db.close()
  }
}
