package controllers

import model.CreateUser
import play.api.Logger
import play.api.libs.json.Json

import scala.concurrent.Future
import play.api.mvc._
import services.{UserService, WebUntisService}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class HomeController(webUntisService: WebUntisService, userStorageService: UserService) extends Controller {

  val U_SERVER = "https://urania.webuntis.com"
  var SCHOOL = ""
  var USER = ""
  var PASSWORD = ""


  def index = Action{
    val user = CreateUser("asdf@asd.ie", "topsret")

//    userStorageService.addUser("test@example.com", "123")

    Ok(userStorageService.isLoginValid(user.email, user.password).toString())
  }


  def getTimetable(server: String, school: String, user: String, password: String, elementType: Int, elementId: Int, date: Int = 20150209) = WUAuth(parse.anyContent, U_SERVER, SCHOOL, USER, PASSWORD) { (request, authCookie, server) =>
    webUntisService.getTimetable(U_SERVER, authCookie, elementType, elementId, date).map {
      timetable => Ok(timetable.body)
    }
  }

  def getList(elementType: Int) = WUAuth(parse.anyContent, U_SERVER, SCHOOL, USER, PASSWORD){ (request, authCookie, server) =>
    webUntisService.getList(server, authCookie, elementType).map(
      result => Ok(result.body)
    )
  }

  def loadLists(server: String = U_SERVER, school: String = SCHOOL, user: String = USER, password: String = PASSWORD) = WUAuth(parse.anyContent, server, school, user, password) {  (request, authCookie, server) =>
    val lists = (1 to 4).map(webUntisService.getList(server, authCookie, _))
    Future.sequence(lists).map(
      response => {
        val data = response.map(e => Json.parse(e.body))
        Ok(Json.arr(data))
      }
    )
  }

  def schoolSearch(searchParam: String) = Action.async{
    webUntisService.schoolSearch(searchParam).map(
      result => Ok(result.body)
    )
  }

  def WUAuth[A](bp: BodyParser[A], server: String, school: String, user: String, password: String)(f: (Request[A], Seq[String], String) => Future[Result]): Action[A] = {
    Action.async(bp) { request =>
      webUntisService.auth2(server, school, user, password).flatMap(
        r => {
          Logger.info(r.allHeaders.toString())
          r.allHeaders.get("Set-Cookie") match {
            case Some(cookie) => {
              f(request, cookie.distinct, server)
            }
            case None => Future {
              Unauthorized("nee")
            }
          }
        }
      )
    }
  }
}
