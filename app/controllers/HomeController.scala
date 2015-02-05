package controllers

import play.api.Logger
import play.api.libs.json.Json

import scala.concurrent.Future
import play.api.mvc._
import services.WebUntisService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class HomeController(webUntisService: WebUntisService) extends Controller {

  val U_SERVER = "https://urania.webuntis.com"
  var SCHOOL = "htl3r"
  var USER = "htl3r"
  var PASSWORD = "htl3r"


  def index = Action{
    Ok(views.html.index())
  }


  def getTimetable = WUAuth(parse.anyContent, U_SERVER, SCHOOL, USER, PASSWORD) { (request, authCookie, server) =>
    webUntisService.getTimetable(U_SERVER, authCookie, 1, 287, 20141208).map {
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

  def WUAuth[A](bp: BodyParser[A], server: String, school: String, user: String, password: String)(f: (Request[A], String, String) => Future[Result]): Action[A] = {
    Action.async(bp) { request =>
      webUntisService.auth2(server, school, user, password).flatMap(
        r => {
          r.header("Set-Cookie") match{
            case Some(x) => {
              Logger.info(s"Auth Cookie: ${x}")
              f(request, x, server)
            }
            case None => Future{Unauthorized("nee")}
          }
        }
      )
    }
  }



}