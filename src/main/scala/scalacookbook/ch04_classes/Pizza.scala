package scalacookbook.ch04_classes
import scala.Predef

/**
  * @author Humayun
  */
object Pizza {
  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"

  def main(args: Array[String]): Unit = {
    val p1 = new Pizza
    println(p1)
  }
}

class Pizza (var crustSize: Int, var crustType: String) {
  def this(crustSize: Int) {
    this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
  }

  def this(crustType: String) {
    this(Pizza.DEFAULT_CRUST_SIZE, crustType)
  }

  def this() {
    this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  }

  override def toString: String = s"$crustSize inch pizza with a $crustType crust."
}
