package services

import model.backend.User
import org.mindrot.jbcrypt.BCrypt
import scaldi.{Injector, Injectable}
import storage.UserStorage


trait UserService{
  def addUser(email: String, password: String): Boolean
  def getUserByEmail(email: String): Option[User]
  def isLoginValid(email: String, password: String): Boolean
  def getAllUser(): List[User]
  def getActivatedUser(): List[User]
  def setUserActivated(user: User)
}

class UserServiceImpl(implicit inj: Injector) extends UserService with Injectable {

  val storage = inject[UserStorage]

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

  override def getActivatedUser(): List[User] = {
    storage.getActivatedUser()
  }

  override def setUserActivated(user: User): Unit = {
    storage.updateUser(user.copy(activatedByAdmin = true, activatedByUser = true))
  }
}
