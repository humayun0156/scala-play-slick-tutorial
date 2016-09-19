implicit def doubleToInt(d: Double): Int = {
  d.toInt
}
val someInt: Int = 2.3
//====================
val oneToTen = 1 to 10
//val oneToTen1 = 1 --> 10
class RangeMaker(left: Int) {
  def -->(right: Int) = left to right
}
val oneToTen2 = new RangeMaker(1) --> 6