/**
  * @author Humayun
  */

object HigherOrderFunction {
  def main(args: Array[String]): Unit = {
    println(
      List(1,2,3,4) map((x: Int) => x + 1)
    )
    println(
      List(1,2,3,4) map(_+1)
    )
    println(
      List(1,2,3,4) map addOne
    )
    /////////
    println(
      map(List(1,2,3,4), (x: Int) => x +1)
    )
    println(
      map1(List(1,2,3,4), (x: Int) => x +1)
    )
    /////////
    println(
      flatMap(List("three", "two", "one")) {_.toList}
    )
  }
  def addOne(num: Int) = {
    def ++ = (x: Int) => x + 1
    ++(num)
  }

  def map[A, B](xs: List[A], f: A => B): List[B] = {
    xs match {
      case List() => List()
      case head :: tail => f(head) :: map(tail, f)
    }
  }

  def map1[A, B](xs: List[A], f: A => B): List[B] = {
    for (x <- xs) yield f(x)
  }

  def flatten[B](xss: List[List[B]]): List[B] = {
    xss match {
      case List() => List.empty[B]//Nil
      case head :: tail => head ::: flatten(tail)
    }
  }
  def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] = {
    flatten(map(xs, f))
  }

  def flatten3[B](xss: List[List[B]]): List[B] = {
    def _flatten3(oldList: List[List[B]], newList: List[B]): List[B] = oldList match {
      case List() => newList
      case head :: tail => _flatten3(tail, newList ::: head)
    }
    _flatten3(xss, List.empty[B])
  }

}
