package fpexamples

/**
  * @author Humayun
  */
object Combinator {
  implicit def kestrel[A](a: A) = new {
    def  tap(sideEffect: A => Unit): A = {
      sideEffect(a)
      a
    }
  }
}
case class Person(firstName: String, lastName: String)
case class Mailer(mailAddress: String) {
  def mail(body: String) = println("send mail here....")
}

object Main {
  import Combinator._
  def main(args: Array[String]): Unit = {
    println(
      Person("humayun", "kabir").tap(p => {
        println(p.firstName)
        Mailer("SomeAddress")
      }).lastName
    )

  }
}
