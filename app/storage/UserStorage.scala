package storage


import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import model.User
import org.bson.types.ObjectId
import org.joda.time.DateTime
import model.mongoContext._ //we need this context ... do not delete!!!

import com.novus.salat.global._

class UserStorage(mongoDb: MongoDB) {

  object UserDAO extends SalatDAO[User, ObjectId](mongoDb(User.DOCUMENT))

  def addUser(email: String, password: String) {
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

}
