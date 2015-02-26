package actors

import play.api.Logger
import provider.UserProvider
import scaldi.Injector
import scaldi.akka.AkkaInjectable
import akka.actor.Actor

class TimedActor(implicit inj: Injector) extends Actor with AkkaInjectable{

  val userProvider: UserProvider = inject[UserProvider]
  val downloadActorRef = injectActorRef[DataFetcher]

  override def receive: Receive = {
    case "ping" => {
      val user = userProvider.getActivatedUser()
      Logger.info(s"---- Hey, ${user.size} user found!")
      user.foreach(u => downloadActorRef ! u)
    }
  }

}
