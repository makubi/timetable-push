package services

import model.User
import org.mindrot.jbcrypt.BCrypt
import play.api.Logger
import scaldi.{Injector, Injectable}
import storage.UserStorage


trait UserService{
  def addUser(email: String, password: String): Boolean
  def getUserByEmail(email: String): Option[User]
  def isLoginValid(email: String, password: String): Boolean
  def getAllUser(): List[User]
}

class UserServiceImpl(implicit inj: Injector) extends UserService with Injectable {

  val storage = inject[UserStorage]
  Logger.info("----------------- init UserService")

  override def addUser(email: String, password: String): Boolean = {
    this.synchronized{
      storage.existsUserWithEmail(email) match{
        case true => false
        case false => {
          val pwdHash = BCrypt.hashpw(password, BCrypt.gensalt())
          storage.addUser(email, pwdHash)
          true
        }
      }
    }
  }

  override def getUserByEmail(email: String): Option[User] = {
    storage.getUserByEmail(email)
  }

  override def isLoginValid(email: String, password: String): Boolean = {
    getUserByEmail(email).map{ user =>
      BCrypt.checkpw(password, user.password)
    }.getOrElse(false)
  }

  override def getAllUser(): List[User] = {
    storage.getAllUser
  }

}
