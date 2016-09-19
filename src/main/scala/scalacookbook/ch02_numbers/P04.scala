package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
//      Replacements for ++ and --
object P04 {
  def main(args: Array[String]): Unit = {
    var a = 1
    a += 1
    p(a)
    a -= 45
    p(a)
    a *= -1
    p(a)
    a /= 10
    p(a)
  }

  def p(x: Any) = println(x)
}
