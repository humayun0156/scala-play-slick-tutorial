val inc = (x: Int) => x + 1
val square = (x: Int) => x * x
val cube = (x: Int) => x * x * x

inc(2)
square(3)
cube(4)

val d: List[Int] =
  List(1, 2, 3).map((x: Int) => x *x)
val d1: List[Int] =
  (1 to 10).toList.map(cube)
val d2 = List(31, 37).map((x: Int) => x + 1)
d(2)
d1(4)
d2(1)

val sT = (x: Char) => x + 1
val s = ('a' to 'z').toList.map(sT)

val d3 = List(1,2,3,4,5,6,7).par.map(square)
d3(1)
val name = "humaYun"
println(name.exists(_.isUpper))

class Programmer(var name: String, var language: String, var favDrink: String)