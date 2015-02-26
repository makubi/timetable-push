package services

import java.util.UUID

import model.UserNotification
import scaldi.{Injectable, Injector}
import storage.UserNotificationStorage
import play.api.db.slick.DB
import play.api.Play.current

trait UserNotificationService {
  def addNotification(userNotification: UserNotification)
  def getNotificationsByUserId(userId: UUID): List[UserNotification]
}

class UserNotificationServiceImpl(implicit inj: Injector) extends UserNotificationService with Injectable{

  val userNotificationStorage: UserNotificationStorage = inject[UserNotificationStorage]

  override def addNotification(userNotification: UserNotification): Unit = {
    DB.withTransaction{ implicit session =>
      userNotificationStorage.addNotification(userNotification)
    }
  }

  override def getNotificationsByUserId(userId: UUID): List[UserNotification] = {
    DB.withSession{ implicit session =>
      userNotificationStorage.getNotififactionByUser(userId)
    }
  }
}
