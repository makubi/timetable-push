package storage

import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import model.TimetableEvent
import org.bson.types.ObjectId
import org.joda.time.DateTime
import scaldi.{Injectable, Injector}
import storage.context


class TimetableEventStorage(implicit inj: Injector) extends Injectable {

  val mongoDb: MongoDB = inject[MongoDB]

  object TimetableEventDAO extends SalatDAO[TimetableEvent, ObjectId](mongoDb(TimetableEvent.DOCUMENT))

  def addEvent(userId: ObjectId, configId: ObjectId, createdAt: DateTime, eventDump: String): Unit ={
    TimetableEventDAO.insert(TimetableEvent(new ObjectId(), userId, configId, createdAt, eventDump))
  }

  def addEvent(timetableEvent: TimetableEvent): Unit = {
    TimetableEventDAO.insert(timetableEvent)
  }

  def getEventsByUserAndConfig(userId: ObjectId, configId: ObjectId): List[TimetableEvent] = {
    TimetableEventDAO.find(MongoDBObject(TimetableEvent.USER_ID_KEY -> userId, TimetableEvent.CONFIG_ID_KEY -> configId)).toList
  }

  def getEventById(eventId: ObjectId): Option[TimetableEvent] = {
    TimetableEventDAO.findOne(MongoDBObject(TimetableEvent.ID_KEY -> eventId))
  }

}
