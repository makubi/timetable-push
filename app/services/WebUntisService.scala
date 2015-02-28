package services

import play.api.libs.ws.WSResponse
import scaldi.{Injector, Injectable}

import scala.concurrent.Future

trait WebUntisService{
  def getTimetable(serverUrl: String, cookie: String, elementType: Int, elementId: Int, date: Int): Future[WSResponse]
  def doAuthentication(serverUrl: String, school: String, username: String, password: String): Future[WSResponse]
  def getElementList(serverUrl: String, authCookie: String, elementType: Int): Future[WSResponse]
  def doSchoolSearch(searchParams: String): Future[WSResponse]
  def getUserData(serverUrl: String, school: String, username: String, password: String): Future[WSResponse]
}

class WebUntisServiceImpl(implicit inj: Injector) extends WebUntisService with Injectable{

  val network = inject[Network]

  def getTimetable(serverUrl: String, cookie: String, elementType: Int, elementId: Int, date: Int) = {
    network.getTimetable(serverUrl, cookie, elementType, elementId, date)
  }

  def doAuthentication(serverUrl: String, school: String, username: String, password: String) = {
    network.authenticate(serverUrl, school, username, password)
  }

  def getElementList(serverUrl: String, authCookie: String, elementType: Int) = {
    network.getList(serverUrl, authCookie, elementType)
  }

  def doSchoolSearch(searchParams: String) = {
    network.schoolSearch(searchParams)
  }

  override def getUserData(serverUrl: String, school: String, username: String, password: String): Future[WSResponse] = {
    network.authenticate2(serverUrl, school, username, password)
  }
}
