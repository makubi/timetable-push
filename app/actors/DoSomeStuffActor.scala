package actors

import akka.actor.Actor
import play.api.Logger

class DoSomeStuffActor extends Actor{
  override def receive: Receive = {
    case e: String => {
//      Logger.info(s"${e} - Ping start")
      Thread.sleep(2000);
//      Logger.info(s"${e} - Ping end")
    }
  }
}
