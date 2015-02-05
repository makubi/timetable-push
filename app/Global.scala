import play.api.{GlobalSettings}
import services.{WebUntisService, Network}
import controllers._

object Global extends GlobalSettings {

  lazy val network = new Network
  lazy val webuntisService = new WebUntisService(network)

  lazy val controllerSingletons = Map[Class[_], AnyRef](
    (classOf[HomeController] -> new HomeController(webuntisService))
  )

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    controllerSingletons(controllerClass).asInstanceOf[A]
  }
}