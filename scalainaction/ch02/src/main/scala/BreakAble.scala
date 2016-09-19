/**
  * @author Humayun
  */
object BreakAble {
  val breakException = new RuntimeException("break exception")
  def breakable(op: => Unit) = {
    try {
      op
    } catch {
      case _ => println("Got Exception")
    }
  }
  def break = throw breakException
  def install = {
    val env = System.getenv("SCALA_HOME")
    if (env == null) {
      break
    }
    println("Found scala_home, let's do the real work")
  }

  def main(args: Array[String]): Unit = {
    breakable{
      install
    }

  }
}
