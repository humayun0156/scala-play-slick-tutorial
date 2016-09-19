package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P13_CaseGuard {
  case class Person(name: String)

  def main(args: Array[String]): Unit = {
    val i = 13
    i match {
      case a if 0 to 10 contains a => println(a)
      case b if 11 to 15 contains b => println("Range 11 to 15")
      case _ => println("Hmm.....")
    }
    i match {
      case x if x == 1 => println("This is one.")
      case x if x == 13 => println("This is thirteen.")
      case _ => println("Hmm... other number")
    }
    speak(Person("Fred"))
  }

  def speak(p: Person) = p match {
    case Person(name) if name == "Fred" => println("Now Fred is speaking.")
    case Person(name) if name == "Bam" => println("Bam Bam")
    case _ => println("Others are speaking....")
  }
}
