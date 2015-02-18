package modules

import actors.TimedActor
import akka.actor.ActorSystem
import com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers
import com.mongodb.casbah.{MongoClient, MongoDB}
import controllers.{AuthController, UserController, HomeController}
import provider.{UserProviderImpl, UserProvider}
import scaldi.Module
import scaldi.play.condition._
import services._
import storage.{TimetableConfigStorage, UserStorage}


class ServiceModule extends Module{
  bind [UserService] when (inDevMode or inTestMode or inProdMode) to new UserServiceImpl
  bind [TimetableConfigService] when (inDevMode or inTestMode or inProdMode) to new TimetableConfigServiceImpl
  bind [WebUntisService] when (inDevMode or inTestMode or inProdMode) to new WebUntisServiceImpl
}

class ProviderModule extends Module{
  bind [UserProvider] when (inDevMode or inTestMode or inProdMode) to new UserProviderImpl
}

class StorageModule extends Module{
  bind [UserStorage] when (inDevMode or inTestMode or inProdMode) to new UserStorage
  bind [TimetableConfigStorage] when (inDevMode or inTestMode or inProdMode) to new TimetableConfigStorage
}

class MongoDbModule extends Module{
  bind [MongoDB] when (inDevMode or inTestMode or inProdMode) to MongoClient("localhost", 27017)("untiscrawler") initWith(db => RegisterJodaTimeConversionHelpers())
}

class NetworkModule extends Module{
  bind [Network] when (inDevMode or inTestMode or inProdMode) to new Network
}

class AkkaModule extends Module{
  bind [ActorSystem] to ActorSystem("UntisCrawler") destroyWith (_.shutdown())
  bind [TimedActor] to new TimedActor
}

class ControllerModule extends Module{
  binding to new HomeController
  binding to new UserController
  binding to new AuthController
}



