package storage

import java.util.UUID

import model.TimetableConfig
import scala.slick.lifted.Tag
import play.api.db.slick.Config.driver.simple._

class TimetableConfigTable(tag: Tag) extends Table[TimetableConfig](tag, TimetableConfig.TABLE){

  def configId = column[UUID](TimetableConfig.ID_KEY, O.PrimaryKey)
  def userId = column[UUID](TimetableConfig.USER_ID_KEY, O.NotNull)
  def url = column[String](TimetableConfig.URL_KEY, O.NotNull)
  def school = column[String](TimetableConfig.SCHOOL_KEY, O.NotNull)
  def elementType = column[Int](TimetableConfig.ELEMENT_TYPE_KEY, O.NotNull)
  def elementId = column[Int](TimetableConfig.ELEMENT_ID_KEY, O.NotNull)
  def userName = column[String](TimetableConfig.USER_KEY, O.NotNull)
  def password = column[String](TimetableConfig.PASSWORD_KEY, O.NotNull)
  def error = column[Boolean](TimetableConfig.ERROR_KEY, O.NotNull)

  override def * = (configId, userId, url, school, elementType, elementId, userName, password, error) <> ((TimetableConfig.apply _).tupled, TimetableConfig.unapply)

  def fk = foreignKey(TimetableConfig.USER_ID_FK, userId, TableQuery[UserTable])(_.userId)
}

class TimetableConfigStorage{

  val table = TableQuery[TimetableConfigTable]

  def addConfig(timetableConfig: TimetableConfig)(implicit session: Session): Unit ={
    table.insert(timetableConfig)
  }

  def getConfigById(configId: UUID)(implicit session: Session): Option[TimetableConfig] = {
    table.filter(_.configId === configId).firstOption
  }

  def getConfigByUserId(userId: UUID)(implicit session: Session): Option[TimetableConfig] = {
    table.filter(_.userId === userId).firstOption
  }

  def updateConfig(timetableConfig: TimetableConfig)(implicit session: Session): Unit ={
    table.filter(_.userId === timetableConfig.userId).update(timetableConfig)
  }

  def deleteConfigByUser(userId: UUID)(implicit session: Session): Unit ={
    table.filter(_.userId === userId).delete
  }
}
