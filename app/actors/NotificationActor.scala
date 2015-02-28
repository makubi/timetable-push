package actors

import akka.actor.Actor
import model.{UiUserBundle, UiTimetableEvent}
import play.api.Logger
import scaldi.Injector
import scaldi.akka.AkkaInjectable

class NotificationActor(implicit inj: Injector) extends Actor with AkkaInjectable{

  // faelle ->
  // activate user by email
  // user activated by admin
  // config failed
  // send events by push/mail

  override def receive: Receive = {
    case data: (List[UiTimetableEvent],UiUserBundle) => notify(data._1, data._2)
  }

  def notify(events: List[UiTimetableEvent], userBundle: UiUserBundle): Unit ={
    Logger.info("new news")
    events.foreach(e => Logger.info("notif: " + e.toString))
  }


}
