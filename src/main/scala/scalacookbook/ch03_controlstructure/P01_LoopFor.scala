package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P01_LoopFor {
  def main(args: Array[String]): Unit = {
    //val a = Array("apple", "banana", "orange")
    val a = List("apple", "banana", "orange")
    for (i <- a) {
      val s = i.toUpperCase
      println(s)
    }

    val newList = for (i <- a) yield i.toUpperCase()
    println(newList)

    val newList_1 = for (i <- a) yield {
      val s = i.toUpperCase
      s
    }
    println(newList_1)

    //for loop counter
    for (i <- 0 until a.length) {
      println(s"$i is ${a(i)}")
    }

    for ((e, count) <- a.zipWithIndex) {
      println(s"$count is $e")
    }

    for (i <- 1 to 10 if i <= 4) println(i)

    val names = Map("fname" -> "Humayun", "lname" -> "kabir")
    for ((k,v) <- names) {
      println(s"$k : $v")
    }

    a.foreach(e => println(e))
    a.foreach{ e =>
      val s = e.toUpperCase()
      println(s)
    }

    1.to(10).foreach(i => println(s"My turn: $i"))

    for (i <- 1 to 10 if i%2 == 0) {
      println(s"Even number: $i")
    }
    1.to(10).withFilter(i => isEven(i)).foreach(e => println(s"WithFilter: $e"))

    for (i <- 1 to 10 if i != 1 if i % 2 == 0) {
      println(i)
    }


  }

  def isEven(n: Int): Boolean = {
    if (n%2 == 0) true
    else false
  }
}
