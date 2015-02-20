package model

import org.bson.types.ObjectId
import org.joda.time.DateTime
import utils.JsonUtil


case class UiUser(email: String, password: String, userId: ObjectId, timeStampCreated: DateTime, activatedByUser: Boolean, activatedByAdmin: Boolean)
object UiUser{
  def apply(u: User): UiUser = UiUser(u.email, u.password, u.userId, u.timeStampCreated, u.activatedByUser, u.activatedByAdmin)
}

case class UiTimetableConfig(configId: ObjectId,userId: ObjectId,url: String,school: String,elementType: Int,elmentId: Int, userName: Option[String] = None, password: Option[String] = None)
object UiTimetableConfig{
  def apply(c: TimetableConfig): UiTimetableConfig = UiTimetableConfig(c.configId, c.userId, c.url, c.school, c.elementType, c.elementId, c.userName, c.password)
}
case class UiUserBundle(uiUser: UiUser, uiTimetableConfig: UiTimetableConfig)

case class UiTimetableEvent(eventId: ObjectId, userId: ObjectId, configId: ObjectId, createdAt: DateTime, timeTableData: MergedTimetablePeriod)
object UiTimetableEvent{
  def apply(e: TimetableEvent): UiTimetableEvent = UiTimetableEvent(e.eventId, e.userId, e.configId, e.createdAt, JsonUtil.jsonToMergedTimetablePeriod(e.rawJsonData).get)
}