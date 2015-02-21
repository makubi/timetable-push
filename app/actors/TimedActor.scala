package actors

import provider.UserProvider
import scaldi.Injector
import scaldi.akka.AkkaInjectable
import akka.actor.Actor

class TimedActor(implicit inj: Injector) extends Actor with AkkaInjectable{

  val userProvider: UserProvider = inject[UserProvider]
  val downloadActorRef = injectActorRef[DataFetcher]

  override def receive: Receive = {
    case "ping" => {
      userProvider.getActivatedUser().foreach(u => downloadActorRef ! u)
    }
  }

}
