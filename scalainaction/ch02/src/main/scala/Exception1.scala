/**
  * @author Humayun
  */
object Exception1 {
  def rangeMatcher(num: Int) = num match {
    case within10 if within10 <= 10 => println("within 0 to 10")
    case within100 if within100 <= 100 && within100 > 11 => println("within 11 to 100")
    case _ => throw new IllegalArgumentException("Only values between 0 to 100 are allowed")
  }

  def main(args: Array[String]): Unit = {
    try {
      //rangeMatcher(4)
      //rangeMatcher(56)
      rangeMatcher(456)
    } catch {
      case e: IllegalArgumentException => println(e.getMessage)
    }
  }
}
