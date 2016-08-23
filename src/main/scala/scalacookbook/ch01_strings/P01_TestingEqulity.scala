package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P01_TestingEqulity {
  def main(args: Array[String]): Unit = {
    val s1 = "Hello"
    val s2 = "Hello"
    val s3 = "H" + "ello"
    println(s1 == s2)
    println(s2 == s3)
    println("Hello".toUpperCase == "hello".toUpperCase)
    println("HELlo".equalsIgnoreCase("hello"))
  }
}
