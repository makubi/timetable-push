package services

import play.api.Play.current
import play.api.libs.json.Json

import play.api.libs.ws._

import scala.concurrent.Future

class Network {

  def getTimetable(serverUrl: String, authCookie: Seq[String], elementType: Int, elementId: Int, date: Int) = {
    val form = Map(
      "ajaxCommand" -> Seq("getWeeklyTimetable"),
      "elementType" -> Seq(s"${elementType}"),
      "elementId" -> Seq(s"${elementId}"),
      "date" -> Seq(s"${date}")
    )
    doTimetableRequest(serverUrl, authCookie, form)
  }

  def getList(serverUrl: String, authCookie: Seq[String], elementType: Int) = {
    val form = Map(
      "ajaxCommand" -> Seq("getPageConfig"),
      "type" -> Seq(s"${elementType}")
    )
    doTimetableRequest(serverUrl, authCookie, form)
  }

  def schoolSearch(searchParams: String) = {
    val url = "https://query.webuntis.com/schoolquery/"
    val body = Json.obj(
      "jsonrpc" -> "2.0",
      "method" -> "searchSchool",
      "id" -> 0,
      "params" -> Json.arr(
        Json.obj(
          "search" -> searchParams
        )
      )
    )
    WS.url(url).post(body)
  }

  def authenticate(serverUrl: String, school: String, username: String, password: String): Future[WSResponse] = {
    val url = s"https://${serverUrl}/WebUntis/j_spring_security_check?request.preventCache=${System.currentTimeMillis()}"

    val form = Map(
      "buttonName" -> Seq("login"),
      "school" -> Seq(school),
      "j_username" -> Seq(username),
      "j_password" -> Seq(password)
    )
    WS.url(url).withFollowRedirects(false).post(form)
  }


  def doTimetableRequest(serverUrl: String, authCookie: Seq[String], requestParams: Map[String, Seq[String]]): Future[WSResponse] = {
    val urlAppendix = "/WebUntis/Timetable.do"
    val url = s"https://${serverUrl+urlAppendix}?request.preventCache=${System.currentTimeMillis()}"
    val cookie = authCookie.foldRight("")((a,b) => a  + (if(!b.isEmpty || !a.isEmpty) ";" else "") + b)
    WS.url(url).withHeaders("Cookie" -> cookie).post(requestParams)
  }

}
