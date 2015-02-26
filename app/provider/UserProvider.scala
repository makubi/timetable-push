package provider

import java.util.UUID

import model.{TimetableConfig, UiUserBundle, UiTimetableConfig, UiUser}
import org.joda.time.DateTime
import play.api.Logger
import scaldi.{Injector, Injectable}
import services.{TimetableConfigService, UserService}


trait UserProvider{
  def getActivatedUser(): List[UiUserBundle]
  def addUser(email: String, password: String): Boolean
  def isEmailRegistered(email: String): Boolean
  def isLoginValid(email: String, password: String): Boolean
  def getUserByEmail(email: String): Option[UiUser]
  def setUserActivatedByEmail(uuid: UUID, l: Long): Boolean
  def setUserBundleFailed(uiUserBundle: UiUserBundle): Unit
}

class UserProviderImpl(implicit inj: Injector) extends UserProvider with Injectable{

  val userService: UserService = inject[UserService]
  val timetableConfigService: TimetableConfigService = inject[TimetableConfigService]

  override def getActivatedUser(): List[UiUserBundle] = {
    userService.getActivatedUser().map{ user =>
      (user, timetableConfigService.getTimetableConfigByUser(user.userId))
    }.filter{ e =>
      (e._2.isDefined && !e._2.get.error)
    }.map{ e =>
      UiUserBundle(
        UiUser(e._1),
        UiTimetableConfig(e._2.get)
      )
    }
  }

  override def addUser(email: String, password: String): Boolean = {
    userService.addUser(email, password)
  }

  override def isEmailRegistered(email: String): Boolean = {
    userService.isUserRegistered(email)
  }

  override def isLoginValid(email: String, password: String): Boolean = {
    userService.isLoginValid(email, password)
  }

  override def getUserByEmail(email: String): Option[UiUser] = {
    userService.getUserByEmail(email).map{
      UiUser(_)
    }
  }

  override def setUserActivatedByEmail(uuid: UUID, timeStamp: Long): Boolean = {
    userService.getUserById(uuid).map{ user =>
      if(user.timeStampCreated.getMillis == timeStamp){
        userService.setUserActivated(user, true, user.activatedByAdmin)
        true
      }else{
        false
      }
    }.getOrElse(false)
  }

  override def setUserBundleFailed(uiUserBundle: UiUserBundle): Unit = {
    val c = uiUserBundle.uiTimetableConfig
    timetableConfigService.setTimetableConfigError(TimetableConfig(c.configId,c.userId,c.url,c.school,c.elementType,c.elmentId,c.userName,c.password,c.error), true)
  }
}
