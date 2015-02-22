package controllers

import play.api.mvc.{Security, Action, Controller}
import scaldi.{Injector, Injectable}
import services.UserService
import views.html

class AuthController(implicit inj: Injector) extends Controller with Injectable{

  val userService: UserService = inject[UserService]
  val forms: Forms = inject[Forms]

  def authenticate = Action { implicit request =>
    forms.loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.user.start(forms.addUserForm, formWithErrors, 0)),
      user => Redirect(routes.UserController.area).withSession(Security.username -> user._1)
    )
  }

  def logout = Action {
    Redirect(routes.HomeController.index).withNewSession
  }

}