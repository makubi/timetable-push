package storage

import java.util.UUID

import model.{UserNotification}
import play.api.db.slick.Config.driver.simple._

class UserNotificationTable(tag: Tag) extends Table[UserNotification](tag, UserNotification.TABLE){

  def notificationId = column[UUID](UserNotification.ID_KEY, O.PrimaryKey)
  def userId = column[UUID](UserNotification.USER_ID, O.NotNull)
  def typee = column[Int](UserNotification.TYPE_KEY, O.NotNull)
  def address = column[String](UserNotification.ADDRESS_KEY, O.NotNull)

  override def * = (notificationId, userId, typee, address) <> ((UserNotification.apply _).tupled, UserNotification.unapply)

  def fk = foreignKey(UserNotification.USER_ID, userId, TableQuery[UserTable])(_.userId)
}

class UserNotificationStorage{
  val table = TableQuery[UserNotificationTable]

  def addNotification(notification: UserNotification)(implicit session: Session): Unit ={
    table.insert(notification)
  }

  def deleteNotification(notification: UserNotification)(implicit session: Session): Unit ={
    table.filter(_.notificationId === notification.notificationId).delete
  }

  def getNotififactionByUser(userId: UUID)(implicit session: Session): List[UserNotification] = {
    table.filter(_.userId === userId).list
  }
}

