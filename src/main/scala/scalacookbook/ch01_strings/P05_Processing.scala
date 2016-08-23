package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P05_Processing {
  def main(args: Array[String]): Unit = {
    val upper1 = "Hello world".map(c => c.toUpper)
    println(upper1)
    val upper2 = "Hello world".map(_.toUpper)
    println(upper2)

    for (c <- "hello")
      println(c)

    val upper3 = for (c <- "Hello, world") yield c.toUpper
    println(upper3)

    val result = for {
      c <- "Hello, world"
      if c != 'l'
    } yield c.toUpper
    println(result)

    println("HELLO".map(toLower))

  }

  def toLower(c: Char): Char = {
    (c.toByte + 32).toChar
  }
}
