/**
  * @author Humayun
  */
object PatternMatch1 {
  def main(args: Array[String]): Unit = {
    ordinal(20)
  }
  def ordinal(num: Int) = num match {
    case 1 => println("1st")
    case 2 => println("2nd")
    case 3 => println("3rd")
    case 4 => println("4th")
    case _ => println("Other than 1 to 4")
  }
}
