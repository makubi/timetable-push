package controllers

import play.api.mvc.{Results, Security, Action, Controller}
import provider.{RecaptchaProvider, UserProvider}

import scaldi.{Injector, Injectable}
import views.html

import play.api.libs.concurrent.Execution.Implicits.defaultContext


import scala.concurrent.Future

class UserController(implicit inj: Injector) extends Controller with Secured with Injectable{

  override val userProvider: UserProvider = inject[UserProvider]
  val recaptchaProvider: RecaptchaProvider = inject[RecaptchaProvider]
  val forms: Forms = inject[Forms]

  def start = Action { implicit request =>
    request.session.get(Security.username).map { user =>
      Results.Redirect(routes.UserController.area)
    }.getOrElse{
      Ok(views.html.user.start(forms.addUserForm, forms.loginForm, 0))
    }
  }

  def area = withAuth { username => implicit request =>
    Ok(views.html.user.area(userProvider.getUserByEmail(username).get))
  }

  def validateCreateUserForm = Action.async { implicit request =>
    val bad = BadRequest(html.user.start(forms.addUserForm.withError("Email", "Captcha Error"), forms.loginForm, 1))
    forms.addUserForm.bindFromRequest.fold(
      formWithErrors => Future{ BadRequest(html.user.start(formWithErrors, forms.loginForm, 1)) },
      user => {
        request.body.asFormUrlEncoded match{
          case Some(map) => {
            map.get("g-recaptcha-response") match {
              case Some(captacha) =>{
                recaptchaProvider.validateCaptcha(captacha.head).map{ success =>
                  if(success){
                    val result = userProvider.addUser(user._1, user._2)
                    Results.Redirect(routes.UserController.area).withSession(Security.username -> user._1)
                  }else{
                    bad
                  }
                }
              }
              case None => Future{ bad }
            }
          }
          case None => Future{ bad }
        }
      }
    )
  }

}
