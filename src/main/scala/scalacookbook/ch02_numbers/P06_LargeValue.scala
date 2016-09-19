package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
object P06_LargeValue {
  def main(args: Array[String]): Unit = {
    val a = BigInt(45345345)
    val b = BigInt(23333333)
    println(a*b)
    val x = BigDecimal(324534234623423432423423423423.4)
    val y = BigDecimal(365345675674345587654335654355345645654566545665.4543)
    println(x * y)
  }
}
