/**
  * @author Humayun
  */
object InfiniteExample {
  def main(args: Array[String]): Unit = {
    // 0 1 1 2 3 5 8 13 21
    println(fib(8))

    //val fib1: Stream[Int]  = Stream.cons(0, Stream.cons(1, fib1.zip(fib1.tail).map(t => t._1 + t._2)))
  }

  def fib(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fib(n-1) + fib(n-2)
  }
}
