package scalacookbook.ch03_controlstructure

import scala.annotation.tailrec
import scala.util.control.Breaks
import scala.util.control.Breaks._

/**
  * @author Humayun
  */
object P05_BreakContinue {
  def main(args: Array[String]): Unit = {
    println("===== Break Example =====")
    breakable {
      for (i <- 1 to 10) {
        println(i)
        if (i > 4) {
          break
        }
      }
    }

    println("==== Continue Example ====")
    val searchMe = "peter piper picked a peck of pickled peppers"
    var numPos = 0
    for (i <- 0 until searchMe.length) {
      breakable{
        if (searchMe.charAt(i) != 'p') {
          break   // break out of breakable, but continue the outer for loop
        } else {
          numPos += 1
        }
      }
    }
    println("Found " + numPos + " p's in the string")

    val Inner = new Breaks
    val Outer = new Breaks
    Outer.breakable{
      for (i <- 1 to 5) {
        Inner.breakable {
          for (c <- 'a' to 'e') {
            if (i == 1 && c == 'c') Inner.break
            else println(s"i: $i, c: $c")
            if (i == 2 && c == 'b') Outer.break
          }
        }
      }
    }

    val a = Array.range(1, 10)
    println(sumToMax(a, 10))

    println("Factorial: " + factorial(6))

    println("FactorialTail: " + factorialTail(6))
  }

  def factorialTail(n: Int): Int = {
    @tailrec
    def loop(i: Int, acc: Int): Int = {
      if (i <= 1) acc
      else  loop(i - 1, i * acc)
    }
    loop(n, 1)
  }

  def factorial(n: Int): Int = {
    if (n == 1) 1
    else n * factorial(n-1)
  }

  def sumToMax(list: Array[Int], limit: Int): Int = {
    var sum = 0
    for (i <- list) {
      sum += list(i)
      if (sum > limit) return limit
    }
    sum
  }
}
