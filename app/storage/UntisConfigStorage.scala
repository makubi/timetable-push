package storage

import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import model.UntisConfig
import org.bson.types.ObjectId
import org.joda.time.DateTime
import scaldi.{Injector, Injectable}
import model.mongoContext._

class UntisConfigStorage(implicit inj: Injector) extends Injectable {

  val mongoDb: MongoDB = inject[MongoDB]

  object UntisConfigDAO extends SalatDAO[UntisConfig, ObjectId](mongoDb(UntisConfig.DOCUMENT))

  def addConfig(userId: ObjectId,
                cookie: String,
                url: String,
                expireDate: DateTime,
                elementType: Int,
                elementId: Int,
                userName: Option[String] = None,
                password: Option[String] = None): Unit ={
    val config = UntisConfig(new ObjectId(), userId, cookie, url, expireDate, elementType, elementId, userName, password)
    UntisConfigDAO.insert(config)
  }

  def getConfigById(configId: ObjectId): Option[UntisConfig] = {
    UntisConfigDAO.findOne(MongoDBObject(UntisConfig.USER_ID_KEY -> configId))
  }

  def getConfigByUserId(userId: ObjectId): List[UntisConfig] = {
    UntisConfigDAO.find(MongoDBObject(UntisConfig.USER_ID_KEY -> userId)).toList
  }

}
