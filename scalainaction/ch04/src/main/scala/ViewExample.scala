import scala.io.Source


/**
  * @author Humayun
  */
object ViewExample {
  def main(args: Array[String]): Unit = {
    val allTweets = Map("Humayun" -> tweets _, "ManningBooks" -> tweets _, "scala"-> tweets _)

    for (t <- allTweets; if t._1 == "scala") t._2(t._1)
  }

  def tweets(handle: String) = {
    println("processing tweets for " + handle)
    val source = Source.fromURL(new java.net.URL("http://search.twitter.com/search.atom?q=" + handle))
    val iterator = source.getLines()
    val builder = new StringBuilder
    for(line <- iterator) builder.append(line)
    //XML.loadString(builder.toString)
  }
}
