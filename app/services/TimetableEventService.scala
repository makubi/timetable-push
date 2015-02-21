package services

import java.util.UUID

import model.TimetableEvent
import scaldi.{Injector, Injectable}
import storage.TimetableEventStorage
import play.api.db.slick.DB
import play.api.Play.current

trait TimetableEventService{
  def addTimetableEvent(timetableEvent: TimetableEvent)
  def getTimetableEvents(userId: UUID, configId: UUID): List[TimetableEvent]
}

class TimetableEventServiceImpl(implicit inj: Injector) extends TimetableEventService with Injectable{
  val timetableEventStorage: TimetableEventStorage = inject[TimetableEventStorage]

  override def addTimetableEvent(timetableEvent: TimetableEvent): Unit = {
    DB.withTransaction{implicit session =>
      timetableEventStorage.addEvent(timetableEvent)
    }
  }

  override def getTimetableEvents(userId: UUID, configId: UUID): List[TimetableEvent] = {
    DB.withSession{implicit session =>
      timetableEventStorage.getEventsByUserAndConfig(userId, configId)
    }
  }
}
