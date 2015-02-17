package actors

import java.util.concurrent.TimeUnit

import akka.actor.{Props, Actor}
import akka.pattern.{ ask, pipe }
import akka.util.Timeout
import play.api.Logger
import play.api.libs.concurrent.Akka
import play.api.Play.current

import scala.concurrent.duration.FiniteDuration

class TimedActor extends Actor{

  var i = 0;
  val doSomeStuff = Akka.system.actorOf(Props[DoSomeStuffActor])
  implicit val timeout = Timeout(FiniteDuration(5, TimeUnit.SECONDS))

  override def receive: Receive = {
    case "ping" => {
      (1 to 2).foreach{j =>
        if(i < 500) {
//          Logger.info(s"Ask Actor No. $i")
          doSomeStuff ? s"${i}"
        }
        i = i + 1
      }
    }
    case _ =>
  }

}
