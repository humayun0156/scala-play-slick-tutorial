package scalacookbook.ch03_controlstructure

import java.io.{FileInputStream, FileOutputStream, IOException}

/**
  * @author Humayun
  */
object P17_TryCatchFinally {
  def main(args: Array[String]): Unit = {
    var in = None: Option[FileInputStream]
    var out = None: Option[FileOutputStream]

    try {
      in = Some(new FileInputStream("C:\\Users\\Humayun\\Documents\\download.htm"))
      out = Some(new FileOutputStream("C:\\Users\\Humayun\\Documents\\download.copy.htm"))
      var c = 0
      while ({c = in.get.read; c != -1}) {
        out.get.write(c)
      }
    } catch {
      case e: IOException => println("IOException.....: " + e.getMessage)
    } finally {
      println("Entered in finally block")
      if (in.isDefined) in.get.close()
      if (out.isDefined) out.get.close()
    }
  }

}
