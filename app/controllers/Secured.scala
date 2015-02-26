package controllers

import model.UiUser
import play.api.mvc._
import provider.UserProvider

import scala.concurrent.Future

trait Secured {

  val userProvider: UserProvider

  def username(request: RequestHeader) = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.UserController.start)

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }

  def withAuthO(f: => Option[UiUser] => Request[AnyContent] => Result) = {
    Action{ request =>
      val data = request.session.get(Security.username)
      val user = data.map(userProvider.getUserByEmail(_)).flatten
      f(user)(request)
    }
  }

  def withAuthOAsync[A](bp: BodyParser[A])(f: (Request[A], Option[UiUser]) => Future[Result]) = {
    Action.async(bp){ request =>
      val data = request.session.get(Security.username)
      val user = data.map(userProvider.getUserByEmail(_)).flatten
      f(request, user)
    }
  }

  def BasicAuth[A](bp: BodyParser[A])(f: (Request[A], String, String) => Result): Action[A] = {
    Action(bp){ request =>
      request.headers.get("Authorization").map { basicAuth =>
        val (user, pass) = decodeBasicAuth(basicAuth)
        if (userProvider.isLoginValid(user, pass)) {
          f(request, user, pass)
        }else{
          Results.Unauthorized
        }
      }.getOrElse{
        Results.Unauthorized
      }
    }
  }

  def decodeBasicAuth(auth: String) = {
    val baStr = auth.replaceFirst("Basic ", "")
    val Array(user, pass) = new String(new sun.misc.BASE64Decoder().decodeBuffer(baStr), "UTF-8").split(":")
    (user, pass)
  }


}