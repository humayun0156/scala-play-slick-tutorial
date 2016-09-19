package scalacookbook.ch02_numbers

import sun.security.util.ByteArrayLexOrder

/**
  * @author Humayun
  */
object P03 {
  def main(args: Array[String]): Unit = {
    p(2d)
    p(1100L.getClass)
    val a = 0:Byte
    p("A: " + a.getClass)
    val b = 0:Double
    p("B: " + b.getClass)
    val s = 0: Short
    p("S: " + s.getClass)

    val l: Int = 234
    p("L: " + l.getClass + " : " + l)
    val m:Double = 435
    p("M: " + m.getClass + " : " + m)
    val n:List[String] = List("Humayun", "Abdullah")
    p("N: " + n.getClass + " = " + n)
    val myMap:Map[String,String] = Map("firstName"->"Humayun", "lastName"->"Kabir")
    p("myMap: " + myMap.getClass + " == " + myMap)
  }
  def p(x: Any) = println(x)
}
