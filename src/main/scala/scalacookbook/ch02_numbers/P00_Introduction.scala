package scalacookbook.ch02_numbers

import java.util.Formatter.DateTime

/**
  * @author Humayun
  */
object P00_Introduction {
  def main(args: Array[String]): Unit = {
    println("Short MaxMin")
    println(Short.MinValue)
    println(Short.MaxValue)
    p("Int MaxMin")
    p(Int.MaxValue)
    p(Int.MinValue)
    p("Long MaxMin")
    p(Long.MaxValue)
    p(Long.MinValue)

  }
  def p(x: Any) = println(x)
}
