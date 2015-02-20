package services

import model.TimetableConfig
import org.bson.types.ObjectId
import scaldi.{Injector, Injectable}
import storage.TimetableConfigStorage

trait TimetableConfigService  {
  def addTimetableConfig(userId: ObjectId, url: String, school: String, elementType: Int,elementId: Int,userName: Option[String] = None,password: Option[String] = None)
  def getTimetableConfigByUser(userId: ObjectId): Option[TimetableConfig]
}

class TimetableConfigServiceImpl(implicit inj: Injector) extends TimetableConfigService with Injectable{
  
  val timetableConfigStorage: TimetableConfigStorage = inject[TimetableConfigStorage]
  
  override def addTimetableConfig(userId: ObjectId, url: String, school: String, elementType: Int,elementId: Int,userName: Option[String] = None,password: Option[String] = None): Unit = {
    timetableConfigStorage.addConfig(userId, url, school, elementType, elementId, userName, password)
  }

  override def getTimetableConfigByUser(userId: ObjectId): Option[TimetableConfig] = {
    timetableConfigStorage.getConfigById(userId)
  }
}
