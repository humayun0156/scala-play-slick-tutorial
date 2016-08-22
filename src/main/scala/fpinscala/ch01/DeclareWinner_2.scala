package fpinscala.ch01

/**
  * @author Humayun
  */
object DeclareWinner_2 {
  case class Player(name: String, score: Int)

  def printWinner(p: Player): Unit = {
    println(p.name + " is the winner")
  }

  def winner(p1: Player, p2: Player): Player = {
    if (p1.score > p2.score) p1 else p2
  }

  def declareWinner(p1: Player, p2: Player): Unit = {
    printWinner(winner(p1, p2))
  }

  def main(args: Array[String]): Unit = {
    val players = List(Player("Abdullah", 30),
                       Player("Ayesha", 20),
                       Player("Humayun", 10))
    val p = players.reduceLeft(winner)
    printWinner(p)
  }

}
