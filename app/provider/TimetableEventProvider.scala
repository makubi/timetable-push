package provider

import model.{TimetableEvent, UiTimetableEvent}
import org.bson.types.ObjectId
import scaldi.{Injector, Injectable}
import services.TimetableEventService
import utils.JsonUtil


trait TimetableEventProvider{
  def getTimetableEvents(userId: ObjectId, configId: ObjectId): List[UiTimetableEvent]
  def addTimetableEvent(timetableEvent: UiTimetableEvent)
}

class TimetableEventProviderImpl(implicit inj: Injector) extends TimetableEventProvider with Injectable{
  val timetableEventService = inject[TimetableEventService]

  override def getTimetableEvents(userId: ObjectId, configId: ObjectId): List[UiTimetableEvent] = {
    timetableEventService.getTimetableEvents(userId, configId).map{ e =>
      UiTimetableEvent(e)
    }
  }

  override def addTimetableEvent(e: UiTimetableEvent): Unit = {
    timetableEventService.addTimetableEvent(TimetableEvent(e.eventId, e.userId, e.configId, e.createdAt, JsonUtil.mergedTimetablePeriodToJson(e.timeTableData)))
  }
}
