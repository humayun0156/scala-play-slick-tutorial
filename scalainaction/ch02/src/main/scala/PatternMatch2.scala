import java.util.Date

/**
  * @author Humayun
  */
object PatternMatch2 {
  def printMatch(obj: AnyRef) = obj match {
    case s: String => println("This is a string")
    case l: List[_] => println("This is a List")
    case a: Array[_] => println("This is an array")
    case d: Date => println("This is a Date")
  }

  def main(args: Array[String]): Unit = {
    printMatch("String")
    printMatch(List(1,2,3))
    printMatch(Array(1,2,3))
    printMatch(new Date)
  }
}

