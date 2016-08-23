package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
class StringImprovements(s: String) {
  def increment = s.map(c => (c+1).toChar)
  def decrement = s.map(c => (c-1).toChar)
  def hideAll: String = s.replaceAll(".", "*")
  def plusOne = s.toInt + 1
  def asBoolean = s match {
    case "0" | "zero" | "" | " " => false
    case _ => true
  }
}

object P10_OwnClass {
  def main(args: Array[String]): Unit = {
    implicit def stringToString(s: String) = new StringImprovements(s)
    println("HAL".increment)
    println("MNO".decrement)
    println("xyz".hideAll)
    println("4".plusOne)
    println("abc".asBoolean)
    println("0".asBoolean)
  }
}
