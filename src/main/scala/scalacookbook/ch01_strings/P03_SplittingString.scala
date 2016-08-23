package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P03_SplittingString {
  def main(args: Array[String]): Unit = {
    println("Hello world".split(" "))
    "Hello world".split(" ").foreach(println)

    val s = "milk, eggs, butter, coco powder"
    s.split(",").foreach(println)
    s.split(",").map(_.trim).foreach(println)
    s.split(" ").foreach(println)
    s.split(' ').foreach(println)
  }
}
