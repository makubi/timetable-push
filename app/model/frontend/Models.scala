package model.frontend

import model.backend.{TimetableConfig, User}
import org.bson.types.ObjectId
import org.joda.time.DateTime


case class UiUser(email: String, password: String, userId: ObjectId, timeStampCreated: DateTime, activatedByUser: Boolean, activatedByAdmin: Boolean)

object UiUser{
  def apply(u: User): UiUser = UiUser(u.email, u.password, u.userId, u.timeStampCreated, u.activatedByUser, u.activatedByAdmin)
}

case class UiTimetableConfig(configId: ObjectId,userId: ObjectId,cookie: String,url: String,expireDate: DateTime,elementType: Int,elmentId: Int, userName: Option[String] = None, password: Option[String] = None)

object UiTimetableConfig{
  def apply(c: TimetableConfig): UiTimetableConfig = UiTimetableConfig(c.configId, c.userId, c.cookie, c.url, c.expireDate, c.elementType, c.elementId, c.userName, c.password)
}

case class UiUserBundle(uiUser: UiUser, uiTimetableConfig: UiTimetableConfig)
