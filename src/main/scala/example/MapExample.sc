val nums = List(1,3,1,4,3,2,5)

nums.filter(_ > 3)
nums.filter(_ % 2 == 0)

nums.map(x => x match {
  case 1 => "One"
  case 2 => "two"
  case _ => "other"
})

val aList = List(1,2,3)
val bList = List(4,5,6)
val res = for {
  a <- aList
  if a > 1
  b <- bList
  if b < 6
} yield a + b
println(res)


val fruits = List("orange", "banana", "apple")
fruits.filter(_.length > 5)
fruits.filter(_.startsWith("a"))
fruits.map(_.length )

trait Person {
  def first: String
  def age: Int
  def valid: Boolean
}

def validByAge(in: List[Person]) =
  in.filter(_.valid).
  map(_.first)