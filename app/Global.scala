import java.util.concurrent.TimeUnit

import actors.TimedActor
import akka.actor.Props
import modules._
import play.api.libs.concurrent.Akka
import play.api.{Application, GlobalSettings}
import scaldi.Injector
import scaldi.play.ScaldiSupport
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration.FiniteDuration


object Global extends GlobalSettings with ScaldiSupport{

  override def applicationModule: Injector = new ControllerModule :: new UserModule :: new MongoDbModule :: new NetworkModule :: new WebUntisModule :: new StorageModule

  override def onStart(app: Application): Unit = {
    super.onStart(app)

    val timedActor = Akka.system.actorOf(Props[TimedActor])
    Akka.system.scheduler.schedule(FiniteDuration.apply(10,TimeUnit.SECONDS), FiniteDuration.apply(1,TimeUnit.SECONDS), timedActor, "ping")
  }

}