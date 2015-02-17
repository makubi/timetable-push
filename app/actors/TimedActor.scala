package actors

import java.util.concurrent.TimeUnit

import akka.actor.{Props, Actor}
import akka.pattern.{ ask, pipe }
import akka.util.Timeout
import play.api.Logger
import play.api.libs.concurrent.Akka
import play.api.Play.current
import scaldi.Injector
import scaldi.akka.AkkaInjectable
import services.UserService

import scala.concurrent.duration.FiniteDuration

class TimedActor(implicit inj: Injector) extends Actor with AkkaInjectable{
  val userService: UserService = inject[UserService]

  var i = 0;
  val doSomeStuff = Akka.system.actorOf(Props[DoSomeStuffActor])
  implicit val timeout = Timeout(FiniteDuration(5, TimeUnit.SECONDS))

  override def receive: Receive = {
    case "ping" => {
      val user = userService.getAllUser()
      Logger.info(user(i % user.size).toString)
      i = i + 1;
    }
    case _ =>
  }

}
