package modules

import actors.{NotificationActor, AnalystActor, DataFetcher, TimedActor}
import akka.actor.ActorSystem
import controllers.{AuthController, UserController, HomeController}
import provider.{TimetableEventProviderImpl, TimetableEventProvider, UserProviderImpl, UserProvider}
import scaldi.Module
import scaldi.play.condition._
import services._
import storage.{TimetableEventStorage, TimetableConfigStorage, UserStorage}


class ServiceModule extends Module{
  bind [UserService] when (inDevMode or inTestMode or inProdMode) to new UserServiceImpl
  bind [TimetableConfigService] when (inDevMode or inTestMode or inProdMode) to new TimetableConfigServiceImpl
  bind [WebUntisService] when (inDevMode or inTestMode or inProdMode) to new WebUntisServiceImpl
  bind [TimetableEventService] when (inDevMode or inTestMode or inProdMode) to new TimetableEventServiceImpl
}

class ProviderModule extends Module{
  bind [UserProvider] when (inDevMode or inTestMode or inProdMode) to new UserProviderImpl
  bind [TimetableEventProvider] when (inDevMode or inTestMode or inProdMode) to new TimetableEventProviderImpl
}

class StorageModule extends Module{
  bind [UserStorage] when (inDevMode or inTestMode or inProdMode) to new UserStorage
  bind [TimetableConfigStorage] when (inDevMode or inTestMode or inProdMode) to new TimetableConfigStorage
  bind [TimetableEventStorage] when (inDevMode or inTestMode or inProdMode) to new TimetableEventStorage
}

class NetworkModule extends Module{
  bind [Network] when (inDevMode or inTestMode or inProdMode) to new Network
}

class AkkaModule extends Module{
  bind [ActorSystem] to ActorSystem("UntisCrawler") destroyWith (_.shutdown())
  bind [TimedActor] to new TimedActor
  bind [DataFetcher] to new DataFetcher
  bind [AnalystActor] to new AnalystActor
  bind [NotificationActor] to new NotificationActor
}

class ControllerModule extends Module{
  binding to new HomeController
  binding to new UserController
  binding to new AuthController
}



