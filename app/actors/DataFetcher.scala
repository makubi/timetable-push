package actors

import akka.actor.Actor
import model.{MergedTimetablePeriod, UiUserBundle}
import play.api.Logger
import scaldi.Injector
import scaldi.akka.AkkaInjectable
import services.WebUntisService
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.{TimetableUtil, JsonUtil}

import scala.concurrent.Future

class DataFetcher(implicit inj: Injector) extends Actor with AkkaInjectable{

  val timetableService = inject[WebUntisService]
  val analystActor = injectActorRef[AnalystActor]

  override def receive: Receive = {
    case user: UiUserBundle => doSomeStuff(user)
  }

  def doSomeStuff(uiBundle: UiUserBundle) = {
    val config = uiBundle.uiTimetableConfig

    timetableService.doAuthentication(config.url, config.school, config.userName.get, config.password.get).map{ auth =>
      auth.allHeaders.get("Set-Cookie") match {
        case Some(cookie) => {
          val cookieString = cookie.distinct.foldRight("")((a,b) => a  + (if(!b.isEmpty || !a.isEmpty) ";" else "") + b)
          Future.sequence(TimetableUtil.getRequestDate().map(timetableService.getTimetable(config.url, cookieString, config.elementType, config.elmentId, _))
          ).map{ data =>
            val results = data.map(r => JsonUtil.parseTimetableResponse(r.body))
            results.foreach{ d =>
              val data = d.get
              val irregularEvents = TimetableUtil.getIrregularEvents(data.data)
              analystActor ! (irregularEvents.map(d => MergedTimetablePeriod(d, data.data.elements)), uiBundle)
            }
          }
        }
        case None => Future {
          Logger(s"Error auth: ${uiBundle.uiUser}")
        }
      }
    }
  }

}
