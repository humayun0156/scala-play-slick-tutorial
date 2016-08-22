package fpinscala.ch02

/**
  * @author Humayun
  */
object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  private def formatAbs(x: Int): String = {
    val msg = "The absolute value of %d is %d"
    return msg.format(x, abs(x))
  }
  private def formatAbs1(x:Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-87))
    println(formatAbs1(-78))
  }
}
