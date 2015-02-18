package provider

import model.frontend.{UiTimetableConfig, UiUser, UiUserBundle}
import scaldi.{Injector, Injectable}
import services.{TimetableConfigService, UserService}


trait UserProvider{
  def getActivatedUser(): List[UiUserBundle]
}

class UserProviderImpl(implicit inj: Injector) extends UserProvider with Injectable{

  val userService: UserService = inject[UserService]
  val timetableConfigService: TimetableConfigService = inject[TimetableConfigService]

  override def getActivatedUser(): List[UiUserBundle] = {
    userService.getActivatedUser().map{ user =>
      (user, timetableConfigService.getTimetableConfigByUser(user.userId))
    }.filter{ e =>
      e._2.isDefined
    }.map{ e =>
      UiUserBundle(
        UiUser(e._1),
        UiTimetableConfig(e._2.get)
      )
    }
  }
}
