package fpinscala.ch02

import scala.annotation.tailrec

/**
  * @author Humayun
  */
object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  def factorial(n: Int): Int = {
    @tailrec
    def go(x: Int, acc: Int): Int = {
      if (x <= 0) acc
      else go(x-1, x*acc)
    }
    go(n, 1)
  }

  private def formatAbs(x: Int): String = {
    val msg = "The absolute value of %d is %d"
    return msg.format(x, abs(x))
  }
  private def formatAbs1(x:Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-87))
    println(formatAbs1(-78))

    println(formatResult("absolute value", -42, abs))
    println(formatResult("factorial", 7, factorial))

    println(formatResult("increment", 7, (x: Int) => x + 1))
    println(formatResult("increment1", 7, (x) => x + 1))
    println(formatResult("increment2", 7, x => x + 1))
    println(formatResult("increment3", 7, _ + 1))
    println(formatResult("", 7, x => {val r = x + 1; r}))

  }
}
