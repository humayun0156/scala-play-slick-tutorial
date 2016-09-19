/**
  * @author Humayun
  */

object ParallelExample {
  def timeOperation(payLoad: => Unit) = {
    val start = System.currentTimeMillis()
    payLoad
    println("Time Taken: " + (System.currentTimeMillis() - start))
  }

  def main(args: Array[String]): Unit = {
    timeOperation((1 to 1000000).toList.par.map(_*2))
    //timeOperation((1 to 1000000).toList.map(_*2))

  }

}

