package fpinscala.ch01

/**
  * @author Humayun
  */
object DeclareWinner {
  case class Player(name: String, score: Int)

  def printWinner(p: Player): Unit = {
    println(p.name + " is the winner")
  }
  def declareWinner(p1: Player, p2: Player): Unit = {
    if (p1.score > p2.score)
      printWinner(p1)
    else
      printWinner(p2)
  }
  def main(args: Array[String]): Unit = {
    val p1 = Player("Humayun", 12)
    val p2 = Player("Abdullah", 20)
    declareWinner(p1, p2)
  }
}
