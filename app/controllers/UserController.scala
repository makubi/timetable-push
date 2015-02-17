package controllers

import play.api.mvc.Controller
import scaldi.{Injectable, Injector}
import services.UserService

class UserController(implicit inj: Injector) extends Controller with Secured with Injectable{

  override val userService: UserService = inject[UserService]

  def index = withAuth { username => implicit request =>
    Ok(s"hey: ${username}")
  }

}
