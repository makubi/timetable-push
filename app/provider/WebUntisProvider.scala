package provider

import play.api.libs.json.{JsValue, Json}
import scaldi.{Injector, Injectable}
import services.WebUntisService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

trait WebUntisProvider {
  def doSchoolQuerty(query: String): Future[JsValue]
  def authenticate(server: String, school: String, username: String, password: String): Future[JsValue]
  def loadList(server: String, school: String, username: String, password: String): Future[JsValue]
}

class WebUntisProviderImpl(implicit inj: Injector) extends WebUntisProvider with Injectable {

  val webuntisService: WebUntisService = inject[WebUntisService]

  override def doSchoolQuerty(query: String): Future[JsValue] = {
    webuntisService.doSchoolSearch(query).map{ response =>
      Json.parse(response.body)
    }
  }

  override def authenticate(server: String, school: String, username: String, password: String): Future[JsValue] = {
//    webuntisService.doAuthentication(server, school, username, password).map{ response =>
//      response
//    }
    Future{ Json.obj() }
  }

  override def loadList(server: String, school: String, username: String, password: String): Future[JsValue] = {
    Future{Json.obj()}
  }
}
