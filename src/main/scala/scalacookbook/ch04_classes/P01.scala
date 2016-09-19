package scalacookbook.ch04_classes

/**
  * @author Humayun
  */
object P01 {
  def main(args: Array[String]): Unit = {
    val p = new Person("Humayun", "Kabir")
    p.firstName = "Ayesh"
    p.lastName = "Shiddiqua"
    p.age = 25
    println(p)
  }
}

class Person(var firstName: String, var lastName: String) {
  println("The constructor begins")

  private val HOME = System.getProperty("user.home")
  var age = 0

  override def toString: String = s"$firstName $lastName is $age years old"
  def printHome() { println(s"HOME: $HOME")}
  def printFullName {println(this)}

  printHome()
  printFullName
  println("Still in the constructor")

}
