package scalacookbook.ch02_numbers

/**
  * @author Humayun
  */
object P07_RandomNumbers {
  def main(args: Array[String]): Unit = {
    val r = scala.util.Random
    println(r.nextInt)
    //limit the random number 0 to 99
    for (i <- 0 to 50) {
      print(r.nextInt(100))
      print(",")
    }
    println("=========== Loop End =========")
    println(r.nextFloat)
    println(r.nextDouble)

    for (i <- 0 to 50) {
      print(r.nextPrintableChar)
      print(" ")
    }
    println()

    var range = 0 to r.nextInt(50)
    println(range)
    range = 0 to r.nextInt(10)
    println(range)

    for (i <- 0 to r.nextInt(10)) yield i * 2
    // scala.collection.immutable.IndexedSeq[Int] = Vector(0, 2, 4, 6, 8, 10, 12, 14, 16)
    // scala.collection.immutable.IndexedSeq[Int] = Vector(0, 2, 4, 6)
    val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
  }
}
