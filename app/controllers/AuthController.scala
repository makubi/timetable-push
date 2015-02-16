package controllers

import play.api.mvc.{Security, Action, Controller}
import services.UserService
import play.api.data.Forms._
import play.api.data.Form
import views.html

class AuthController(userService: UserService) extends Controller{

  val loginForm = Form(
    tuple(
      "username" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => userService.isLoginValid(email, password)
    })
  )

  def login = Action { implicit request =>
    request.session.get(Security.username).map { user =>
      Redirect(routes.UserController.index())
    }.getOrElse {
      Ok(html.user(loginForm))
    }
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.user(formWithErrors)),
      user => Redirect(routes.UserController.index).withSession(Security.username -> user._1)
    )
  }

  def logout = Action {
    Redirect(routes.HomeController.index).withNewSession
  }

}