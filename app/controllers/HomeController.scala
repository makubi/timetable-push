package controllers

import play.api.libs.json.Json
import provider.UserProvider
import scaldi.{Injector, Injectable}

import scala.concurrent.Future
import play.api.mvc._
import services.{Network, TimetableConfigService, UserService, WebUntisService}
import play.api.libs.concurrent.Execution.Implicits.defaultContext


class HomeController(implicit inj: Injector) extends Controller with Injectable{

  val webUntisService: WebUntisService = inject[WebUntisService]
  val userStorageService: UserService = inject[UserService]
  val configService: TimetableConfigService = inject[TimetableConfigService]
  val network: Network = inject[Network]

  val userProvider: UserProvider = inject[UserProvider]

  val U_SERVER = "https://urania.webuntis.com"
  var SCHOOL = ""
  var USER = ""
  var PASSWORD = ""

  def index = Action{ implicit request =>
    request.session.get(Security.username).map { user =>
      userProvider.getUserByEmail(user).map { uiUser =>
        Ok(views.html.authenticated.index(uiUser))
      }.getOrElse{
        Ok(views.html.anonymous.index()).withNewSession
      }
    }getOrElse{
      Ok(views.html.anonymous.index())
    }
  }


  //TODO Deprevated ....
  def getTimetable(server: String, school: String, user: String, password: String, elementType: Int, elementId: Int, date: Int = 20150209) = WUAuth(parse.anyContent, U_SERVER, SCHOOL, USER, PASSWORD) { (request, authCookie, server) =>
    webUntisService.getTimetable(U_SERVER, authCookie, elementType, elementId, date).map {
      timetable => Ok(timetable.body)
    }
  }

  def getList(elementType: Int) = WUAuth(parse.anyContent, U_SERVER, SCHOOL, USER, PASSWORD){ (request, authCookie, server) =>
    webUntisService.getElementList(server, authCookie, elementType).map(
      result => Ok(result.body)
    )
  }

  def loadLists(server: String = U_SERVER, school: String = SCHOOL, user: String = USER, password: String = PASSWORD) = WUAuth(parse.anyContent, server, school, user, password) {  (request, authCookie, server) =>
    val lists = (1 to 4).map(webUntisService.getElementList(server, authCookie, _))
    Future.sequence(lists).map(
      response => {
        val data = response.map(e => Json.parse(e.body))
        Ok(Json.arr(data))
      }
    )
  }

  def schoolSearch(searchParam: String) = Action.async{
    webUntisService.doSchoolSearch(searchParam).map(
      result => Ok(result.body)
    )
  }

  def WUAuth[A](bp: BodyParser[A], server: String, school: String, user: String, password: String)(f: (Request[A], String, String) => Future[Result]): Action[A] = {
    Action.async(bp) { request =>
      webUntisService.doAuthentication( server, school, user, password).flatMap(
        r => {
          r.allHeaders.get("Set-Cookie") match {
            case Some(cookie) => {
              val cookieString = cookie.distinct.foldRight("")((a,b) => a  + (if(!b.isEmpty || !a.isEmpty) ";" else "") + b)
              f(request, cookieString, server)
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
