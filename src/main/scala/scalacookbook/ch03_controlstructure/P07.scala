package scalacookbook.ch03_controlstructure

/**
  * @author Humayun
  */
object P07 {
  def main(args: Array[String]): Unit = {
    val i = 5
    i match {
      case 1 | 2 | 3 => println("i: " + i)
      case 4 | 5 | 6 => println("i: " + i)
    }

    val command = "stop"
    command match {
      case "start" | "go" => println("Started")
      case "stop" | "end" => println("Stopped")
    }

    executeCommand(Start)
  }

  def executeCommand(cmd: Command) = cmd match {
    case Start | Go => start()
    case Stop | End => stop()
  }
  def start() = println("started")
  def stop() = println("stopped")
}

trait Command
case object Start extends Command
case object Go extends Command
case object Stop extends Command
case object End extends Command

