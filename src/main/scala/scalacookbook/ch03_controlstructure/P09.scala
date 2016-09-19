package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P09 {
  def main(args: Array[String]): Unit = {
    val evenOrOdd = 4 match {
      case 2|4|6 => "Even"
      case 1|3 => "Odd"
    }
    println(evenOrOdd)

    def isTrue(x: Any) = x match {
      case 0 | "" => false
      case _ => true
    }
    println(isTrue(45))
  }
}
