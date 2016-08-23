package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P02_MultilineString {
  def main(args: Array[String]): Unit = {
    val foo =
      """This is
a multiline
        string
      """
    println(foo)

    val bar =
      """Four score and
        |seven years ago.
      """.stripMargin
    println(bar)

    val bar1 =
      """Four score and
        #Seven years ago.
      """.stripMargin('#')
    println(bar1)

    val speech =
      """Four score and
        |seven years ago
        |and our father
      """.stripMargin.replaceAll("\n", " ")
    println(speech)
  }
}
