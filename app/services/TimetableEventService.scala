package services

import model.TimetableEvent
import org.bson.types.ObjectId
import scaldi.{Injector, Injectable}
import storage.TimetableEventStorage

trait TimetableEventService{
  def addTimetableEvent(timetableEvent: TimetableEvent)
  def getTimetableEvents(userId: ObjectId, configId: ObjectId): List[TimetableEvent]
}

class TimetableEventServiceImpl(implicit inj: Injector) extends TimetableEventService with Injectable{
  val timetableEventStorage: TimetableEventStorage = inject[TimetableEventStorage]

  override def addTimetableEvent(timetableEvent: TimetableEvent): Unit = {
    timetableEventStorage.addEvent(timetableEvent)
  }

  override def getTimetableEvents(userId: ObjectId, configId: ObjectId): List[TimetableEvent] = {
    timetableEventStorage.getEventsByUserAndConfig(userId, configId)
  }
}
