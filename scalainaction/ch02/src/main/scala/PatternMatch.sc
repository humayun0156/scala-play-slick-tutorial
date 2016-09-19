List(1,2,3) match {
  case f::s::rest => List(f,s)
  case _ => Nil
}

val suffixes = List("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th")
def ordinal(num: Int) = num match {
  case tenTo20 if 10 to 20 contains tenTo20 => num + " th"
  case rest => rest + suffixes(num % 10)
}
ordinal(4)
ordinal(3)
ordinal(45)