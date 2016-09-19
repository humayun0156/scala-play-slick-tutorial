package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P15_ListMatch {
  def main(args: Array[String]): Unit = {
    val l = List(1, 2, 3)
    val m = 1 :: 2 :: 3 :: Nil
    val fruits = "Apples" :: "Bananas" :: "Oranges" :: Nil
    println(listToString(fruits))
    println(sum(List(1,2,3,4)))
    println(product(List(3,4)))
  }

  def listToString(list: List[String]): String = list match {
    case s :: rest => s + " " + listToString(rest)
    case Nil => ""
  }

  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case n :: rest => n + sum(rest)
  }

  def product(list: List[Int]): Int = list match {
    case Nil => 1
    case n :: rest => n * product(rest)
  }
}
