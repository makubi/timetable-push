package storage

import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import model.TimetableConfig
import org.bson.types.ObjectId
import scaldi.{Injector, Injectable}
import storage.context

class TimetableConfigStorage(implicit inj: Injector) extends Injectable {

  val mongoDb: MongoDB = inject[MongoDB]

  object UntisConfigDAO extends SalatDAO[TimetableConfig, ObjectId](mongoDb(TimetableConfig.DOCUMENT))

  def addConfig(userId: ObjectId,
                url: String,
                school: String,
                elementType: Int,
                elementId: Int,
                userName: Option[String] = None,
                password: Option[String] = None): Unit ={
    val config = TimetableConfig(new ObjectId(), userId, url, school, elementType, elementId, userName, password)
    UntisConfigDAO.insert(config)
  }

  def getConfigById(configId: ObjectId): Option[TimetableConfig] = {
    UntisConfigDAO.findOne(MongoDBObject(TimetableConfig.USER_ID_KEY -> configId))
  }

  def getConfigByUserId(userId: ObjectId): List[TimetableConfig] = {
    UntisConfigDAO.find(MongoDBObject(TimetableConfig.USER_ID_KEY -> userId)).toList
  }

}
