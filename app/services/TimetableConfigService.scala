package services

import java.util.UUID

import model.TimetableConfig
import scaldi.{Injector, Injectable}
import storage.TimetableConfigStorage
import play.api.db.slick.DB
import play.api.Play.current

trait TimetableConfigService  {
  def addTimetableConfig(userId: UUID, url: String, school: String, elementType: Int,elementId: Int,userName: String, password: String)
  def getTimetableConfigByUser(userId: UUID): Option[TimetableConfig]
}

class TimetableConfigServiceImpl(implicit inj: Injector) extends TimetableConfigService with Injectable{
  
  val timetableConfigStorage: TimetableConfigStorage = inject[TimetableConfigStorage]
  
  override def addTimetableConfig(userId: UUID, url: String, school: String, elementType: Int,elementId: Int,userName: String, password: String): Unit = {
    val config = TimetableConfig(UUID.randomUUID(), userId, url, school, elementType, elementId, userName, password)
    DB.withTransaction{ implicit session =>
      timetableConfigStorage.addConfig(config)
    }
  }

  override def getTimetableConfigByUser(userId: UUID): Option[TimetableConfig] = {
    DB.withSession{ implicit session =>
      timetableConfigStorage.getConfigByUserId(userId)
    }
  }
}
