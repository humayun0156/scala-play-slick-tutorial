def toList[A](value: A) = List(value)

toList(1)
toList("Scala")
//list of tuple
toList("Sca", "la")
val evenNumbers = List(2,4,6,8,10)
evenNumbers.foldLeft(0){(a,b) => a + b}
evenNumbers.foldLeft(1)(_*_)
evenNumbers.product
evenNumbers.sum
val name = "huMayun"
name.exists(_.isUpper)
