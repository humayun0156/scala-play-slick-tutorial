package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P06_FindingPatterns {
  def main(args: Array[String]): Unit = {
    val numPattern = "[0-9]+".r
    val address = "123 Main Street Suite 101"
    val match1 = numPattern.findFirstIn(address)
    println(match1)
    val matches = numPattern.findAllIn(address)
    println(matches)
    matches.foreach(println)


    val addr = "No address given"
    val match2 = numPattern.findFirstIn(addr)
    println(match2)
    val result = numPattern.findFirstIn(addr).getOrElse("no match")
    println(result)

    numPattern.findFirstIn(address).foreach { e =>
      // perform the next step in your algorithm,
      // operating on the value 'e'
    }

    match1 match {
      case Some(s) => println(s"Found: $s")
      case None =>
    }
  }
}
