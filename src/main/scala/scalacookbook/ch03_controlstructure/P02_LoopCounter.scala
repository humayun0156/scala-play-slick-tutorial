package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P02_LoopCounter {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 3; j <- i to 3) {
      println(s"$i : $j")
    }
    for {
      i <- 1 to 3
      j <- i-1 to 3
    } {
      println(s"i: $i, j: $j")
    }

    val array = Array.ofDim[Int](2, 2)
    array(0)(0) = 0
    array(0)(1) = 1
    array(1)(0) = 2
    array(1)(1) = 3
    for (i <- 0 to 1; j <- 0 to 1) {
      println(s"($i)($j) = ${array(i)(j)}")
    }
    println(f(3, List(1,2,3,4)))
    println(f2(3, List(10,4,2,5,6,1,4,0)))
    println(f3(List(10,4,2,5,6,1,4,0)))
  }

  def f(num:Int, arr:List[Int]):List[Int] = {
    for {
      i <- arr
      j <- 1 to num
    } yield i
  }

  def f2(delim:Int,arr:List[Int]):List[Int] = {
    for (i <- arr if i < delim) yield i
  }

  def f3(arr:List[Int]):List[Int] = {
    for ((i, count) <- arr.zipWithIndex if count % 2 != 0) yield i
  }
}
