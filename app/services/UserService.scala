package services

import model.User
import org.mindrot.jbcrypt.BCrypt
import storage.UserStorage

class UserService(storage: UserStorage) {

  def addUser(email: String, password: String): Boolean = {
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

  def getUserByEmail(email: String): Option[User] = {
    storage.getUserByEmail(email)
  }

  def isLoginValid(email: String, password: String): Boolean = {
    getUserByEmail(email).map{ user =>
      BCrypt.checkpw(password, user.password)
    }.getOrElse(false)
  }

  def getAllUser(): List[User] = {
    storage.getAllUser
  }

}
