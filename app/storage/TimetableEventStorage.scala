package storage

import java.util.UUID

import model.TimetableEvent
import org.joda.time.DateTime
import play.api.db.slick.Config.driver.simple._
import com.github.tototoshi.slick.JdbcJodaSupport._


class TimetableEventTable(tag: Tag) extends Table[TimetableEvent](tag, TimetableEvent.TABLE){

  def eventId = column[UUID](TimetableEvent.ID_KEY, O.PrimaryKey)
  def userId = column[UUID](TimetableEvent.USER_ID_KEY, O.NotNull)
  def configId = column[UUID](TimetableEvent.CONFIG_ID_KEY, O.NotNull)
  def createdAt = column[DateTime](TimetableEvent.DATETIME_KEY, O.NotNull)
  def rawJson = column[String](TimetableEvent.RAW_TIMETABLE_EVENT, O.NotNull, O.DBType("VARCHAR(200000)"))

  override def * = (eventId, userId, configId, createdAt, rawJson) <> ((TimetableEvent.apply _).tupled, TimetableEvent.unapply)

  def uFk = foreignKey(TimetableEvent.FK_CONFIG, userId, TableQuery[UserTable])(_.userId)
  def cFk = foreignKey(TimetableEvent.FK_USER, configId, TableQuery[TimetableConfigTable])(_.configId)

}

class TimetableEventStorage {

  val table = TableQuery[TimetableEventTable]

  def addEvent(event: TimetableEvent)(implicit session: Session): Unit ={
//    TimetableEvent(new ObjectId(), userId, configId, createdAt, eventDump)
    table.insert(event)
  }

  def getEventsByUserAndConfig(userId: UUID, configId: UUID)(implicit session: Session): List[TimetableEvent] = {
    table.filter(d => (d.userId === userId && d.configId === configId)).list
  }

  def getEventById(eventId: UUID)(implicit session: Session): Option[TimetableEvent] = {
    table.filter(_.eventId === eventId).firstOption
  }

  def deleteEvents(userId: UUID, configId: UUID)(implicit session: Session): Unit = {
    table.filter(t => (t.userId === userId && t.configId === configId)).delete
  }

}
