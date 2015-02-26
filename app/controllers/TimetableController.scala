package controllers

import play.api.mvc.{Controller}
import provider.{WebUntisProvider, UserProvider}
import scaldi.{Injector, Injectable}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future


class TimetableController(implicit inj: Injector) extends Controller with Injectable with Secured{

  override val userProvider: UserProvider = inject[UserProvider]
  val webuntisProvider: WebUntisProvider = inject[WebUntisProvider]

  def schoolSearch(query: String) = withAuthOAsync(parse.anyContent) { (request, user) =>
    user match {
      case Some(x) => {
        webuntisProvider.doSchoolQuerty(query).map(Ok(_))
      }
      case None => Future{ Unauthorized }
    }
  }

  def untisAuth(u: String, p: String) = withAuthOAsync(parse.anyContent){ (request, user) =>
    user match {
      case Some(x) => {
        Future{ Ok  }
      }
      case None => Future{ Unauthorized }
    }
  }

  def loadLists(se: String, sc: String, u: String, p: String) = withAuthOAsync(parse.anyContent) { (request, user) =>
    user match{
      case Some(x) => Future{ Ok }
      case None => Future{ Unauthorized }
    }
  }
}
