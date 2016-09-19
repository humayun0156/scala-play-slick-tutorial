package fpinscala.ch02

import scala.annotation.tailrec

/**
  * @author Humayun
  */
object Factorial {

  def factorial(n: Int): Int = {
    @tailrec
    def go(x: Int, acc: Int): Int = {
      if (x <= 0) acc
      else go(x-1, x*acc)
    }
    go(n, 1)
  }

  def main(args: Array[String]): Unit = {
    println(factorial(4))
  }
}
