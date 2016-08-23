package scalacookbook.ch01_strings

/**
  * @author Humayun
  */

object P04_Substituting {
  case class Student(name: String, score: Int)

  def main(args: Array[String]): Unit = {

    val name = "Humayun"
    val age = 30
    val weight = 78
    println(s"$name is $age years old, and weighs $weight pounds.")
    println(s"Age next year ${age+1}")
    println(s"You are 33 years old: ${age == 33}")

    val hannah = Student("Hannah", 95)
    println(s"${hannah.name} has a score of ${hannah.score}")
    // error: this is intentionally wrong
    println(s"$hannah.name has a score of $hannah.score")

    println(f"$name is $age years old, and weighs $weight%.2f pounds.")
    println(f"$name is $age years old, and weighs $weight%.0f pounds.")

    println("%s is %d years old".format(name, age))
  }
}
