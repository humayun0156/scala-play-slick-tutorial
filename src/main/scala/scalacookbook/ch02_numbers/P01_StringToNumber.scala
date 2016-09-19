package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
object P01_StringToNumber {
  /*implicit class StringToInt(s: String) {
    @throws(classOf[NumberFormatException])
    def toInt(radix: Int) = Integer.parseInt(s, radix)
  }*/
  def main(args: Array[String]): Unit = {
    p("100".toInt)
    p("199".toDouble)
    p("423".toFloat)
    //p("344df".toInt) //NumberFormatException
    val b = BigDecimal("2304809238409238409")
    val c = BigInt("5324")
    p(Integer.parseInt("123", 8))


    p(toInt("123").getOrElse(0))
    p(toInt("a").getOrElse(0))

    val aString = "555q"
    toInt(aString) match {
      case Some(n) => p(n)
      case None => p("Boom! That wasn't a number")
    }
    val result = toInt(aString) match {
      case Some(n) => n
      case None => 0
    }
    p(result)
  }
  def p(x: Any) = println(x)

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }
}
