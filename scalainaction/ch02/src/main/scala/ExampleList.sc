val array = new Array[String](3)

array(0) = "This"
array(1) = "is"
array(2) = "Mutable"
array.foreach(println)

val oldList = List(1,2)
val newList = 4 :: oldList
val anotherList = newList :+ 5
oldList
newList
anotherList
val stList = "This" ::"is"::"list" :: Nil
stList

val afterDelete = anotherList.filterNot(_ == 5)
afterDelete
