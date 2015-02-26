package controllers

import play.api.data.Form
import play.api.data.Forms._
import provider.UserProvider
import scaldi.{Injector, Injectable}

class Forms(implicit inj: Injector) extends Injectable{

  val userProvider = inject[UserProvider]

  val addUserForm = Form(
    tuple (
      "Email" -> email,
      "Password" -> nonEmptyText,
      "Re-enter password" -> nonEmptyText
    ) verifying ("Invalid email or password", result => result match {
      case (e, p1, p2) => {
        (!userProvider.isEmailRegistered(e) && p1.equals(p2))
      }
    })
  )

  val loginForm = Form(
    tuple(
      "Email" -> text,
      "Password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => userProvider.isLoginValid(email, password)
    })
  )

}
