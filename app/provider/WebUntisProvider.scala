package provider

import play.api.libs.json.{JsValue, Json}
import scaldi.{Injector, Injectable}
import services.WebUntisService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

trait WebUntisProvider {
  def doSchoolQuerty(query: String): Future[JsValue]
  def authenticate(server: String, school: String, username: String, password: String): Future[Option[String]]
  def loadList(server: String, school: String, username: String, password: String): Future[JsValue]
  def loadUserData(server: String, school: String, username: String, password: String): Future[JsValue]
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
            Json.arr(response.map(e => Json.parse(e.body)))
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
}
