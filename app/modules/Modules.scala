package modules

import com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers
import com.mongodb.casbah.{MongoClient, MongoDB}
import controllers.{AuthController, UserController, HomeController}
import scaldi.Module
import scaldi.play.condition._
import services.{Network, WebUntisService, UserServiceImpl, UserService}
import storage.UserStorage

class UserModule extends Module{
  bind [UserService] when (inDevMode or inTestMode or inProdMode) to new UserServiceImpl
}

class WebUntisModule extends Module{
  bind [WebUntisService] when (inDevMode or inTestMode or inProdMode) to new WebUntisService()
}

class StorageModule extends Module{
  bind [UserStorage] when (inDevMode or inTestMode or inProdMode) to new UserStorage
}

class MongoDbModule extends Module{
  bind [MongoDB] when (inDevMode or inTestMode or inProdMode) to MongoClient("localhost", 27017)("untiscrawler") initWith (db => RegisterJodaTimeConversionHelpers())
}

class NetworkModule extends Module{
  bind [Network] when (inDevMode or inTestMode or inProdMode) to new Network
}

class ControllerModule extends Module{
  binding to new HomeController()
  binding to new UserController()
  binding to new AuthController()
}



