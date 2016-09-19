package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
object P02_ConvertingBetNumbers {
  def main(args: Array[String]): Unit = {
    val b = 323.23.toInt
    val a = 1000L
    p(b)
    p(a.isValidLong)
    p(a.isValidByte)
    p(234324.toLong)
    p(23.toDouble)
  }

  def p(x: Any) = println(x)
}
