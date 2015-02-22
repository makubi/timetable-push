package services

import java.util.UUID

import model.User
import org.joda.time.DateTime
import org.mindrot.jbcrypt.BCrypt
import scaldi.{Injector, Injectable}
import storage.UserStorage
import play.api.db.slick.DB
import play.api.Play.current

trait UserService{
  def addUser(email: String, password: String): Boolean
  def getUserByEmail(email: String): Option[User]
  def isUserRegistered(email: String): Boolean
  def isLoginValid(email: String, password: String): Boolean
  def getAllUser(): List[User]
  def getActivatedUser(): List[User]
  def setUserActivated(user: User)
}

class UserServiceImpl(implicit inj: Injector) extends UserService with Injectable {

  val storage = inject[UserStorage]

  override def addUser(email: String, password: String): Boolean = {
    DB.withTransaction { implicit session =>
      storage.existsUserWithEmail(email) match{
        case true => false
        case false => {
          val pwdHash = BCrypt.hashpw(password, BCrypt.gensalt())
          storage.addUser(User(email, pwdHash, UUID.randomUUID(), DateTime.now(),false, false))
          true
        }
      }
    }
  }

  override def getUserByEmail(email: String): Option[User] = {
    DB.withSession{ implicit  session =>
      storage.getUserByEmail(email)
    }
  }

  override def isLoginValid(email: String, password: String): Boolean = {
    getUserByEmail(email).map { user =>
      BCrypt.checkpw(password, user.password)
    }.getOrElse(false)
  }

  override def getAllUser(): List[User] = {
    DB.withSession { implicit session =>
      storage.getAllUser
    }
  }

  override def getActivatedUser(): List[User] = {
    DB.withSession { implicit session =>
      storage.getActivatedUser()
    }
  }

  override def setUserActivated(user: User): Unit = {
    DB.withTransaction { implicit session =>
      storage.updateUser(user.copy(activatedByAdmin = true, activatedByUser = true))
    }
  }

  override def isUserRegistered(email: String): Boolean = {
    DB.withSession{ implicit session =>
      storage.existsUserWithEmail(email)
    }
  }
}
