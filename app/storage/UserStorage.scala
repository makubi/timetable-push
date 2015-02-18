package storage

import com.mongodb.WriteConcern
import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import model.backend.User
import org.bson.types.ObjectId
import org.joda.time.DateTime
import model.mongoContext._ //we need this context ... do not delete!!!
import scaldi.{Injector, Injectable}

import com.novus.salat.global._

class UserStorage(implicit inj: Injector) extends Injectable{

  val mongoDb: MongoDB = inject[MongoDB]

  object UserDAO extends SalatDAO[User, ObjectId](mongoDb(User.DOCUMENT))

  def addUser(email: String, password: String) = {
      val u = User(email, password, new ObjectId(),DateTime.now())
      UserDAO.insert(u)
  }

  def getUserByEmail(email: String): Option[User] = {
    UserDAO.findOne(MongoDBObject(User.EMAIL_KEY -> email))
  }

  def existsUserWithEmail(email: String): Boolean = {
    getUserByEmail(email).isDefined
  }

  def getAllUser(): List[User] = {
    UserDAO.find(MongoDBObject.empty).toList
  }

  def getActivatedUser(): List[User] = {
    UserDAO.find(MongoDBObject(User.ACTIVATED_BY_ADMIN_KEY -> true, User.ACTIVATED_BY_USER_KEY -> true)).toList
  }

  def updateUser(updatedUser: User) = {
    UserDAO.update(MongoDBObject(User.ID_KEY -> updatedUser.userId), updatedUser, false, false, new WriteConcern)
  }

}
