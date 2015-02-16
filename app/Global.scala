import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers
import play.api.{GlobalSettings}
import services.{UserService, WebUntisService, Network}
import controllers._
import storage.UserStorage


object Global extends GlobalSettings {


//  lazy val redisConnection = new RedisClient("localhost", 6379)
  lazy val mongoClient = MongoClient("localhost", 27017)("untiscrawler")
  RegisterJodaTimeConversionHelpers()

  lazy val userStorage = new UserStorage(mongoClient)
  lazy val userStorageService = new UserService(userStorage)

  lazy val network = new Network
  lazy val webuntisService = new WebUntisService(network)

  lazy val controllerSingletons = Map[Class[_], AnyRef](
    (classOf[HomeController] -> new HomeController(webuntisService, userStorageService)),
    (classOf[UserController] -> new UserController(userStorageService)),
    (classOf[AuthController] -> new AuthController(userStorageService))
  )

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    controllerSingletons(controllerClass).asInstanceOf[A]
  }
}