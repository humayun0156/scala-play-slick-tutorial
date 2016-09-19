package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
object P05_ComparingFloat {

  def main(args: Array[String]): Unit = {
    val a = 0.3
    val b = 0.1 + 0.2
    println(a)
    println(b)
    println(MathUtil.~=(a, b, 0.0001))
  }

}

object MathUtil {
  def ~=(x: Double, y: Double, precision: Double): Boolean = {
    if ( (x - y).abs < precision ) true
    else false
  }
}
