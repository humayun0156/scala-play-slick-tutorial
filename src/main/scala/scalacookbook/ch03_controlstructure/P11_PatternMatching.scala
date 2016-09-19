package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P11_PatternMatching {
  def main(args: Array[String]): Unit = {
    toInt("123a") match {
      case Some(i) => println(i)
      case None => println("That wasn't an int")
    }
  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  def echoWhatYouGaveMe(x: Any): String = x match {
    //constant patterns
    case 0 => "zero"
    case true => "true"
    case "hello" => "you said hello"
    case Nil => "an empty list"
    //sequence patterns
    case List(0, _, _) => "a three element list with 0 as the first element"
    case List(1, _*) => "a list beginning with 1, having any number of elements"
    case Vector(1, _*) => "a vector starting with 1, having any number of elements"

    //tuples
    case (a, b) => s"got $a and $b"
    case (a, b, c) => s"got $a, %b and $c"

    //constructor patterns
    //case Person1(fname, "Humayun") => s"found a kabir, with firstName: $fname"
    //case Dog("Suka") => "found a dog named Suka"




  }

  class Person1(firstName: String, lastName: String)
  class Dog(name: String)
}

