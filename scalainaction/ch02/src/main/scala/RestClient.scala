
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}
import org.apache.http.message.BasicHeader

/**
  * @author Humayun
  */
object RestClient {
  def main(args: Array[String]): Unit = {
    require(args.length >= 2, "at minimum you should specify an action ( GET, POST, DELETE, OPTIONS) and an URL")
    val command = args.head
    val params = parseArgs(args)
    val url = args.last

    command match {
      //case "post" => handlePostRequest
      case "get" => handleGetRequest(params, url)
      //case "delete" => handleDeleteRequest
      //case "option" => handleOptionRequest
    }
  }

  def parseArgs(args: Array[String]): Map[String, List[String]] = {
    def nameValuePair(paramName: String) = {
      def values(commaSeparatedValues: String) = commaSeparatedValues.split(",").toList

      val index = args.indexWhere(_ == paramName)
      (paramName, if (index == -1) Nil else values(args(index+1)))
    }

    Map(nameValuePair("-d"), nameValuePair("-h"))
  }

  def splitByEqual(nameValue: String): Array[String] = nameValue.split("=")

  def headers(params: Map[String, List[String]]) = for(nameValue <- params("-h")) yield {
    def tokens = splitByEqual(nameValue)
    new BasicHeader(tokens(0), tokens(1))
  }

  /*def handleOptionRequest = {
    ???
  }

  def handleDeleteRequest = {
    ???
  }

  def handlePostRequest = {
    ???
  }*/

  def handleGetRequest(params: Map[String, List[String]], url: String) = {
    val query = params("-d").mkString("&")
    val httpget = new HttpGet(s"${url}?${query}")
    headers.foreach { httpget.addHeader(_) }
    val responseBody = new DefaultHttpClient().execute(httpget, new BasicResponseHandler())
    println(responseBody)
  }
}
