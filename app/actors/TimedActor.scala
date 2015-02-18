package actors

import java.util.concurrent.TimeUnit

import akka.actor.{Props, Actor}
import akka.util.Timeout
import play.api.Logger
import play.api.libs.concurrent.Akka
import play.api.Play.current
import provider.UserProvider
import scaldi.Injector
import scaldi.akka.AkkaInjectable

import scala.concurrent.duration.FiniteDuration

class TimedActor(implicit inj: Injector) extends Actor with AkkaInjectable{

  val userProvider: UserProvider = inject[UserProvider]

  var i = 0
  val doSomeStuff = Akka.system.actorOf(Props[DoSomeStuffActor])
  implicit val timeout = Timeout(FiniteDuration(5, TimeUnit.SECONDS))

  override def receive: Receive = {
    case "ping" => {
      val user = userProvider.getActivatedUser()
      Logger.info(user.map(_.toString).foldLeft("")(_ + "\n" + _))
      //get all user --> send to download actor
      //get config --> download == success ?  send to compare actor : (error == 401) ? msg to notification center : log
      //compare actor, get all old notifications, check if new ... yes --> notifcation center

    }
    case _ =>
  }

}
