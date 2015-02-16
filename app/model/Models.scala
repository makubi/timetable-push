package model

import com.novus.salat.annotations.Key
import org.bson.types.ObjectId

import org.joda.time.DateTime

object User{
  final val DOCUMENT = "user"
  final val EMAIL_KEY = "email"
  final val PASSWORD_KEY = "password"
  final val ID_KEY = "_id"
  final val DATETIME_KEY = "dateTime"
}

case class User(@Key(User.EMAIL_KEY)      email: String,
                @Key(User.PASSWORD_KEY)   password: String,
                @Key(User.ID_KEY)         userId: ObjectId,
                @Key(User.DATETIME_KEY)   dateTime: DateTime)

object UntisConfig{
  final val DOCUMENT = "untisconfig"
  final val COOKIE_KEY = "cookie"
  final val EXPIRE_DATE = "expireDate"
  final val URL_KEY = "url"
  final val ELEMENT_TYPE_KEY = "elementType"
  final val ELEMENT_ID_KEY = "elementId"
  final val USER_KEY = "untisuser"
  final val PASSWORD_KEY = "untispassword"
}

case class UntisConfig(
                       @Key(User.ID_KEY)                  userId: ObjectId,
                       @Key(UntisConfig.COOKIE_KEY)       cookie: String,
                       @Key(UntisConfig.URL_KEY)          url: String,
                       @Key(UntisConfig.EXPIRE_DATE)      expireDate: DateTime,
                       @Key(UntisConfig.ELEMENT_TYPE_KEY) elementType: Int,
                       @Key(UntisConfig.ELEMENT_ID_KEY)   elmentId: Int,
                       @Key(UntisConfig.USER_KEY)         userName: Option[String],
                       @Key(UntisConfig.PASSWORD_KEY)     password: Option[String]
                        )


case class Notification(
                         @Key(User.ID_KEY)  userId: ObjectId
                         )




