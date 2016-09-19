package fpexamples

/**
  * @author Humayun
  */
object Example {
  def main(args: Array[String]): Unit = {

  }

  def intToChar: PartialFunction[Int, Char] = {
    case 1 => 'a'
    case 2 => 'b'
    case _ => 'c'
  }
}
