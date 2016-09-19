package scalacookbook.ch13_akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * @author Humayun
  */
class HelloActor extends Actor{
  override def receive = {
    case "hello" => println("hello back to you")
    case _ => println("huh?")
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    val helloActor = system.actorOf(Props[HelloActor], "helloactor")

    helloActor ! "hello"
    helloActor ! "hehehe"

    system.terminate()
  }
}
