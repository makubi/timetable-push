package actors

import akka.actor.Actor
import model.{UiTimetableEvent, UiUserBundle, MergedTimetablePeriod}
import org.bson.types.ObjectId
import org.joda.time.DateTime
import play.api.Logger
import provider.TimetableEventProvider
import scaldi.Injector
import scaldi.akka.AkkaInjectable

class AnalystActor(implicit inj: Injector) extends Actor with AkkaInjectable{

  val timetableEventProvider: TimetableEventProvider = inject[TimetableEventProvider]
  val notificationActor = injectActorRef[NotificationActor]

  override def receive: Receive = {
    case data: (List[MergedTimetablePeriod], UiUserBundle) => analyseData(data._1, data._2)
  }

  def analyseData(data: List[MergedTimetablePeriod], userBundle: UiUserBundle): Unit = {

    val userId = userBundle.uiUser.userId
    val configId = userBundle.uiTimetableConfig.configId

    val d = timetableEventProvider.getTimetableEvents(userBundle.uiUser.userId, userBundle.uiTimetableConfig.configId)
    val newPeriods = data.filter(e => !d.map(_.timeTableData).contains(e))
    val events = newPeriods.map(p => UiTimetableEvent(new ObjectId(), userId, configId, DateTime.now(),p))

    Logger.info(s"${userBundle.uiTimetableConfig.school}: Event count db: ${d.size}")
    Logger.info(s"${userBundle.uiTimetableConfig.school}: Event count nw: ${data.size}")
    Logger.info(s"${userBundle.uiTimetableConfig.school}: Event count diff: ${newPeriods.size}")

    events.isEmpty match{
      case false => {
        events.foreach { e =>
         timetableEventProvider.addTimetableEvent(e)
        }
        notificationActor ! (events, userBundle)
      }
    }

  }


}
