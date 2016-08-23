package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P07_ReplacingPatterns {
  def main(args: Array[String]): Unit = {
    val address = "123 Main Street".replaceAll("[0-9]", "x")
    val regex = "[0-9]".r
    val newAddress = regex.replaceAllIn("123 Main Street", "x")
    println(newAddress)
    val result = "123".replaceFirst("[0-9]", "x")
    println(result)

    val reg = "H".r
    val res = reg.replaceFirstIn("Hello world", "J")
    println(res)



  }
}
