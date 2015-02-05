package services


class WebUntisService(network: Network) {


  def getTimetable(serverUrl: String, cookie: String, elementType: Int, elementId: Int, date: Int) = {
    network.getTimetable(serverUrl, cookie, elementType, elementId, date)
  }

  def auth2(serverUrl: String, school: String, username: String, password: String) = {
    network.authenticate(serverUrl, school, username, password)
  }

  def getList(serverUrl: String, authCookie: String, elementType: Int) = {
    network.getList(serverUrl, authCookie, elementType)
  }

  def schoolSearch(searchParams: String) = {
    network.schoolSearch(searchParams)
  }

}
