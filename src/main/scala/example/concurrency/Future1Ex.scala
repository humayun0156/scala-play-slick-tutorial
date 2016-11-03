package example.concurrency


import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
/**
  * @author Humayun
  */
object Future1Ex {
  def main(args: Array[String]): Unit = {
    longRun(23).onComplete{
      case Success(result) => println(s"Result: $result")
      case Failure(e) => e.printStackTrace()
    }
    Thread.sleep(1000)
  }

  def longRun(i: Int): Future[Int] = Future {
    Thread.sleep(100)
    i + 1
  }

}
