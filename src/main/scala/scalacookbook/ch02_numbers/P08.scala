package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
object P08 {
  def main(args: Array[String]): Unit = {
    val r = 1 to 10
    println(r + " == " + r.getClass)
    val rx = 0 to 20 by 2
    println(rx + " == " + r.getClass)

    for (i <- 1 to 10) {
      print(i)
      print(" ")
    }
    println()
    for (i <- 1 until 10) {
      print(i)
      print(" ")
    }
    println("========")
    for (i <- 1 to 20 by 3) {
      print(i)
      print(" ")
    }
    println("#######")
    val m = (1 to 10).toList
    println(m)
    val n = (1 to 10).toArray
    println(n.getClass)



  }
}
