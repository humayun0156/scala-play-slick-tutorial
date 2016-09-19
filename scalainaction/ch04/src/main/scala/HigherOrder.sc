def map[A, B](xs: List[A], f: A => B): List[B] = {
  xs match {
    case List() => List()
    case head :: tail => f(head) :: map(tail, f)
  }
}

def map1[A, B](xs: List[A], f: A => B): List[B] = {
  for (x <- xs) yield f(x)
}

map(List(1,2,3,4), (x: Int) => x +1)
map1(List(1,2,3,4), (x: Int) => x +1)

List("one", "two", "three").map(_.toList)
List("one", "two", "three").flatMap(_.toList)