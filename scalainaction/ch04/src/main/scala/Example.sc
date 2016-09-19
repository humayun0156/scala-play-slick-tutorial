
def position[A](xs: List[A], value: A): Int = {
  xs.indexOf(value)
}

val list = List("One", "Two", "Three")
position(list, "Two")
val intList = List(1, 2, 3, 4, 5)
position(intList, 4)
position[Int](intList, 56)

List("one", "two", "three", "four", "five").zip(Stream.from(0))