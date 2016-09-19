/**
  * @author Humayun
  */
object PositionExample {
  def main(args: Array[String]): Unit = {
    println("--------  1  -----")
    println(
      position(List(), "something").getOrElse(-1)
    )
    println("--------  2  -----")
    println(
      position(List("one", "two", "three"), "three").getOrElse(-1)
    )
  }

  def position[A](xs: List[A], value: A): Maybe[Int] = {
    val index = xs.indexOf(value)
    if (index != -1) Just(index) else Nil
  }

  def defaultToNull[A <: Maybe[_]](p: A) = {
    p.getOrElse(null)
  }
}
