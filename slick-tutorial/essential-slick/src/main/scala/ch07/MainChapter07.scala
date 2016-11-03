package ch07

import slick.driver.H2Driver.api._
import ch00.Main._
import ch00._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble

/**
  * @author Humayun
  */
object MainChapter07 {
  def main(args: Array[String]): Unit = {


    try {
      exec(populate)
      var query = sql"""select "sender" from "message"""".as[String]
      //Await.result(db.run(query), 2 seconds).foreach(println)
      val res = Await.result( db.run(query), 2 seconds )
      println(res.to[List])

      implicit val messageResult = GetResult(m => Message(m.<<, m.<<, m.<<))
      var query_1 = sql"""select * from "message"""".as[Message]
      val res_1 = Await.result( db.run(query_1), 2 seconds )
      println(res_1.to[List])

      val q = messages.filter(_.sender === "HAL").result
      println(exec(q))

    } finally db.close()
  }
}
