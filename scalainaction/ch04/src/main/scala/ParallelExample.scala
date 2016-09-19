import scala.collection.parallel.immutable.ParVector
import scala.collection.parallel.mutable.ParArray

/**
  * @author Humayun
  */
object ParallelExample {
  def main(args: Array[String]): Unit = {
    val x = ParArray(1,2,3,4).sum
    println(x)
    val vs = Vector.range(1, 100000)
    val v = vs.par.filter(_ % 2 == 0).seq
    println(v)
    conversion
  }

  def conversion = {
    timedOperation {
      ParVector.range(1, 10000000).sum
    }
    timedOperation {
      Array.range(1, 10000000).sum
    }

  }

  def timedOperation[T](f: => T) = {
    val startTime = System.currentTimeMillis
    f
    println("Time Taken: " + (System.currentTimeMillis - startTime))
  }
}
