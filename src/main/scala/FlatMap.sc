val fruits = Seq("Mango", "Banana", "Orange")
fruits.map(_.toUpperCase)
fruits.flatMap(_.toUpperCase)

val l = List(1, 2, 3, 4, 5)
l.map(_ * 2)
l.map(x => x * 2)

def f(x: Int) =
  if (x > 1 && x < 5) Some(x * 2)
  else None

l.map(x => f(x))

def g(v: Int) = List(v-1, v, v+1)
l.map(x => g(x))
l.flatMap(x => g(x))

val map = Map(1->"one", 2->"two", 3->"three")
1 to map.size flatMap map.get