package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P06_Case {
  def main(args: Array[String]): Unit = {
    println(getClassAsString("String"))
    println(getClassAsString(123))
    println(getClassAsString(2221.98f))
    println(getClassAsString(List(1,2,3)))
    println(getClassAsString(new Person("Humayun")))
    println(getClassAsString(Array(1,2)))
  }

  def getClassAsString(x: Any): String = x match {
    case s: String => s + " is a String"
    case i: Int => "Int"
    case f: Float => "Float"
    case b: Byte => "Byte"
    case l: List[_] => "List"
    case p: Person => "Person"
    case _ => "Unknown"
  }
}
class Person(name: String)
