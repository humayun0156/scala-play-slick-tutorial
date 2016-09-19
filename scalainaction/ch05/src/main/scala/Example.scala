/**
  * @author Humayun
  */
object Example {
  def main(args: Array[String]): Unit = {
    println(
      singleExpression
    )
  }

  def singleExpression: List[String] => (List[Int], List[Int]) = {
    a => a map (_.toInt) partition(_ < 30)
  }
}
