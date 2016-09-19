package scalacookbook.ch03_controlstructure

import java.io.{FileNotFoundException, IOException}


/**
  * @author Humayun
  */
object P016_TryCatch {
  def main(args: Array[String]): Unit = {
    val s = "foo"
    try {
      val i = s.toInt
    } catch {
      case e: Exception => e.printStackTrace()
    }

    try {
      openAndReadFile("abc")
    } catch {
      case e: FileNotFoundException => println("File Not found")
      case e: IOException => println("had an exception while trying to read the file")
    }
  }

  def openAndReadFile(name: String) = {

  }
}
