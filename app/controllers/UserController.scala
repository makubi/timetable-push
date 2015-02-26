package controllers

import play.api.mvc.{Results, Security, Action, Controller}
import provider.{RecaptchaProvider, UserProvider}

import scaldi.{Injector, Injectable}
import views.html

import play.api.libs.concurrent.Execution.Implicits.defaultContext


import scala.concurrent.Future

class UserController(implicit inj: Injector) extends Controller with Secured with Injectable with ControllerUtils{

  override val userProvider: UserProvider = inject[UserProvider]
  val recaptchaProvider: RecaptchaProvider = inject[RecaptchaProvider]
  val forms: Forms = inject[Forms]

  def start = withAuthO { user => implicit request =>
    user match{
      case Some(uiUser) => Ok(views.html.authenticated.user(uiUser))
      case None => Ok(views.html.anonymous.user(forms.addUserForm, forms.loginForm, 0))
    }
  }

  def addConfig = withAuthO { user => implicit request =>
    user match {
      case Some(uiUser) => Ok(views.html.authenticated.addConfig(uiUser))
      case None => Redirect(routes.UserController.start())
    }
  }

  def activate(timestamp: Long, id: String) = Action { implicit request =>
    parseUUID(id,
      uuid => {
        if(userProvider.setUserActivatedByEmail(uuid, timestamp)){
          Redirect(routes.UserController.start)
        }else{
          Redirect(routes.HomeController.index)
        }
      }, f => {
        Redirect(routes.HomeController.index)
      }
    )
  }

  def authenticate = Action { implicit request =>
    forms.loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.user.start(forms.addUserForm, formWithErrors, 0)),
      user => Redirect(routes.UserController.start).withSession(Security.username -> user._1)
    )
  }

  def logout = Action {
    Redirect(routes.HomeController.index).withNewSession
  }

  def validateCreateUserForm = Action.async { implicit request =>
    def bad(errorMsg: String = "Captcha Error") = BadRequest(html.user.start(forms.addUserForm.withError("Email", errorMsg), forms.loginForm, 1))
    forms.addUserForm.bindFromRequest.fold(
      formWithErrors => Future{ BadRequest(html.user.start(formWithErrors, forms.loginForm, 1)) },
      user => {
        request.body.asFormUrlEncoded match{
          case Some(map) => {
            map.get("g-recaptcha-response") match {
              case Some(captacha) =>{
                recaptchaProvider.validateCaptcha(captacha.head).map{ success =>
                  if(success){
                    val added = userProvider.addUser(user._1, user._2)
                    if(added){
                      Results.Redirect(routes.UserController.start()).withSession(Security.username -> user._1)
                    }else{
                      bad("User already exists")
                    }
                  }else{
                    bad()
                  }
                }
              }
              case None => Future{ bad() }
            }
          }
          case None => Future{ bad() }
        }
      }
    )
  }
}
