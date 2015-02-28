package provider

import model.TimetableResponse
import play.api.libs.json.{JsObject, JsValue, Json}
import scaldi.{Injector, Injectable}
import services.WebUntisService
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.{JsonUtil, TimetableUtil}

import scala.concurrent.Future
import scala.util.parsing.json.{JSONObject, JSONArray}

trait WebUntisProvider {
  def doSchoolQuerty(query: String): Future[JsValue]
  def authenticate(server: String, school: String, username: String, password: String): Future[Option[String]]
  def loadList(server: String, school: String, username: String, password: String): Future[JsValue]
  def loadUserData(server: String, school: String, username: String, password: String): Future[JsValue]
  def loadTimetable(server: String, school: String, username: String, password: String, elementType: Int, elementId: Int): Future[Option[List[Option[TimetableResponse]]]]
}

class WebUntisProviderImpl(implicit inj: Injector) extends WebUntisProvider with Injectable {

  val webuntisService: WebUntisService = inject[WebUntisService]

  override def doSchoolQuerty(query: String): Future[JsValue] = {
    webuntisService.doSchoolSearch(query).map{ response =>
      Json.parse(response.body)
    }
  }

  override def authenticate(server: String, school: String, username: String, password: String): Future[Option[String]] = {
    webuntisService.doAuthentication(checkUrl(server), school, username, password).map{ response =>
      response.allHeaders.get("SET-COOKIE").map{ cookie =>
        cookie.distinct.foldRight("")((a,b) => a  + (if(!b.isEmpty || !a.isEmpty) ";" else "") + b)
      }
    }
  }

  override def loadList(server: String, school: String, username: String, password: String): Future[JsValue] = {
    authenticate(checkUrl(server), school, username, password).flatMap { cookie =>
      cookie match {
        case Some(c) => {
          val listFutures = Future.sequence((1 to 4).toList.map(webuntisService.getElementList(checkUrl(server), c, _)))
          listFutures.map { response =>
            Json.arr(response.map{ e =>
              Json.parse(e._2.body).as[JsObject] ++ Json.obj("elementType" -> e._1)
            })
          }
        }
        case None => {
          Future {
            Json.obj()
          }
        }
      }
    }
  }

  override def loadUserData(server: String, school: String, username: String, password: String): Future[JsValue] = {
    webuntisService.getUserData(checkUrl(server), school, username, password).map{ response =>
      Json.parse(response.body)
    }
  }

  private def checkUrl(url: String): String = {
    if(!url.startsWith("http")){
      s"https://${url}"
    }else{
      url
    }
  }

  override def loadTimetable(server: String, school: String, username: String, password: String, elementType: Int, elementId: Int): Future[Option[List[Option[TimetableResponse]]]] = {
    authenticate(server, school, username, password).flatMap{ auth =>
      auth match {
        case Some(cookie) => {
          val blub = Future.sequence(TimetableUtil.getRequestDate().map(webuntisService.getTimetable(server, cookie, elementType, elementId, _)).map{ data =>
            data.map(r => JsonUtil.parseTimetableResponse(r.body))
          })
          blub.map(Some(_))
        }
        case None => Future { None }
      }
    }
  }
}
