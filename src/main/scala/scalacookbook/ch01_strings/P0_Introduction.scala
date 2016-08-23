package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P0_Introduction {
  def main(args: Array[String]): Unit = {
    val s = "Hello World"
    println(s.getClass.getName)
    println(s.length)

    s.foreach(println)

    for (c <- s)
      print(c)

    println()

    s.getBytes.foreach(println)

    println(s.filter(_ != 'l'))

    println("scala".drop(2).take(2).capitalize)
  }
}
