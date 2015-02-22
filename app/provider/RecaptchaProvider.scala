package provider

import scaldi.{Injector, Injectable}
import services.Network

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext


trait RecaptchaProvider{
  def validateCaptcha(response: String): Future[Boolean]
}

class RecaptchaProviderImpl(implicit inj: Injector) extends RecaptchaProvider with Injectable{

  val network: Network = inject[Network]

  override def validateCaptcha(response: String): Future[Boolean] = {
    network.validateCaptcha(response).map{ response =>
      val json = response.json
      (json \ "success").as[Boolean]
    }
  }

}
