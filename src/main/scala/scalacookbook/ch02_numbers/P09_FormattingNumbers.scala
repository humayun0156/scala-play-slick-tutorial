package scalacookbook.ch02_numbers
import java.util.{Currency, Locale}
/**
  * @author Humayun
  */
object P09_FormattingNumbers {
  def main(args: Array[String]): Unit = {
    val pi = scala.math.Pi
    p(pi)
    println(f"$pi%1.5f")
    println(f"$pi%08.5f")

    var formatter = java.text.NumberFormat.getIntegerInstance
    p(formatter.format(1000000000))

    val locale = new java.util.Locale("de", "DE")
    formatter = java.text.NumberFormat.getIntegerInstance(locale)
    println(formatter.format(233444503.06))

    val de = Currency.getInstance(locale)
    println(de)
    formatter.setCurrency(de)
    println(formatter.format(223124))
  }
  def p(s: Any) = println(s)
}
