package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P04_ForYield {
  def main(args: Array[String]): Unit = {
    val names = List("humayun", "kabir", "abdullah")
    val capNames = for (e <- names) yield e.capitalize
    println(capNames)
    val lengths = for (e <- names) yield e.length
    println(lengths)
    val out = names.map(_.toUpperCase)
    println(out)
  }
}
