package programminginscala.ch01

/**
  * @author Humayun
  */
object Sample {
  def main(args: Array[String]): Unit = {
    var capital = Map("US" -> "Washington", "France" -> "Paris")
    capital += ("Japan" -> "Tokyo")
    println(capital("France"))

    var family = Map("father" -> "Humayun", "mother" -> "Ayesha")
    family += ("child" -> "Abdullah")
    println(family("child"))

    println(factorial(400))
  }

  def factorial(x: BigInt): BigInt = {
    if (x == 0) 1
    else x * factorial(x-1)
  }
}
