package controllers

import play.api.mvc.{Controller, Action}
import services.UserService

class UserController(userSer: UserService) extends Controller with Secured {

  override val userService: UserService = userSer

  def index = withAuth { username => implicit request =>
    Ok(s"hey: ${username}")
  }

}
