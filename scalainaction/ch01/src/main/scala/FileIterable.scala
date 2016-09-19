
/**
  * @author Humayun
  */
class FileIterable {
  def lines = scala.io.Source.fromFile("E:\\projects\\scala\\scala-tutorial\\src\\main\\scala\\scalainaction\\ch01\\someFile.txt").getLines()
}
object FileIterable {
  def main(args: Array[String]): Unit = {
    println(new FileIterable().lines.foreach(x => println(x)))
  }
}
