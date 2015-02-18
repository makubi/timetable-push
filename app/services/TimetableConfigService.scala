package services

import model.backend.TimetableConfig
import org.bson.types.ObjectId
import scaldi.{Injector, Injectable}
import storage.TimetableConfigStorage

trait TimetableConfigService  {
  def addUntisConfig()
  def getTimetableConfigByUser(userId: ObjectId): Option[TimetableConfig]
}

class TimetableConfigServiceImpl(implicit inj: Injector) extends TimetableConfigService with Injectable{
  
  val timetableConfigStorage: TimetableConfigStorage = inject[TimetableConfigStorage]
  
  override def addUntisConfig(): Unit = {
    //timetableConfigStorage.addConfig()
  }

  override def getTimetableConfigByUser(userId: ObjectId): Option[TimetableConfig] = {
    timetableConfigStorage.getConfigById(userId)
  }
}
