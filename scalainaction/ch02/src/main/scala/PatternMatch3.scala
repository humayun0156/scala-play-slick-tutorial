/**
  * @author Humayun
  */
object PatternMatch3 {
  def rangeMatcher(num: Int) = num match {
    case within10 if within10 <= 10 => println("within 0 to 10")
    case within100 if within100 <= 100 && within100 > 11 => println("within 11 to 100")
    case beyond100 if beyond100 > 100 => println("beyond 100")
  }

  def main(args: Array[String]): Unit = {
    rangeMatcher(3)
    rangeMatcher(45)
    rangeMatcher(345)
  }
}
