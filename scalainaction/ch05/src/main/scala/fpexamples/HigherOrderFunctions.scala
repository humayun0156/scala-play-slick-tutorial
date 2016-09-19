package fpexamples

/**
  * @author Humayun
  */
object HigherOrderFunctions {
  def main(args: Array[String]): Unit = {
    val l = List(1,4,3,5,6,9,7,8,2)
    println(for (i <- l; if i % 2 == 0) yield i)
    println(l.filter(_ % 2 == 0))
  }
}
