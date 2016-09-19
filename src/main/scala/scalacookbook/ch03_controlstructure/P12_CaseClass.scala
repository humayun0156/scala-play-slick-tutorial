package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
trait Animal
case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal
case object Woodpecker extends Animal

object P12_CaseClass {
  def main(args: Array[String]): Unit = {
    println(determineType( Dog("Tiger")))
    println(determineType( Cat("") ))
    println(determineType(Woodpecker))
  }

  def determineType(x: Animal): String = x match {
    case Dog(nm) => "Got a dog, named: " + nm
    case _:Cat => "Got a cat, ignoring the name"
    case Woodpecker => "That was a woodpecker."
    case _ => "Something else"
  }

}
