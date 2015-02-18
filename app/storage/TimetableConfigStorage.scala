package storage

import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import model.backend.TimetableConfig
import org.bson.types.ObjectId
import org.joda.time.DateTime
import scaldi.{Injector, Injectable}
import model.mongoContext._

class TimetableConfigStorage(implicit inj: Injector) extends Injectable {

  val mongoDb: MongoDB = inject[MongoDB]

  object UntisConfigDAO extends SalatDAO[TimetableConfig, ObjectId](mongoDb(TimetableConfig.DOCUMENT))

  def addConfig(userId: ObjectId,
                cookie: String,
                url: String,
                expireDate: DateTime,
                elementType: Int,
                elementId: Int,
                userName: Option[String] = None,
                password: Option[String] = None): Unit ={
    val config = TimetableConfig(new ObjectId(), userId, cookie, url, expireDate, elementType, elementId, userName, password)
    UntisConfigDAO.insert(config)
  }

  def getConfigById(configId: ObjectId): Option[TimetableConfig] = {
    UntisConfigDAO.findOne(MongoDBObject(TimetableConfig.USER_ID_KEY -> configId))
  }

  def getConfigByUserId(userId: ObjectId): List[TimetableConfig] = {
    UntisConfigDAO.find(MongoDBObject(TimetableConfig.USER_ID_KEY -> userId)).toList
  }

}
