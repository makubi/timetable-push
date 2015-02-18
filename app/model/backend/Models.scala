package model.backend

import com.novus.salat.annotations.Key
import org.bson.types.ObjectId
import org.joda.time.DateTime

case class User(@Key(User.EMAIL_KEY)      email: String,
                @Key(User.PASSWORD_KEY)   password: String,
                @Key(User.ID_KEY)         userId: ObjectId,
                @Key(User.TIMESTAMP_CREATED_KEY)   timeStampCreated: DateTime,
                @Key(User.ACTIVATED_BY_USER_KEY)   activatedByUser: Boolean = false,
                @Key(User.ACTIVATED_BY_ADMIN_KEY)  activatedByAdmin: Boolean = false
                 )

object User{
  final val DOCUMENT = "user"
  final val EMAIL_KEY = "email"
  final val PASSWORD_KEY = "password"
  final val ID_KEY = "_id"
  final val TIMESTAMP_CREATED_KEY = "dateTime"
  final val ACTIVATED_BY_USER_KEY = "activatedByUser"
  final val ACTIVATED_BY_ADMIN_KEY = "activatedByAdmin"
}

case class TimetableConfig(
                        @Key(TimetableConfig.ID_KEY)           configId: ObjectId,
                        @Key(TimetableConfig.USER_ID_KEY)      userId: ObjectId,
                        @Key(TimetableConfig.COOKIE_KEY)       cookie: String,
                        @Key(TimetableConfig.URL_KEY)          url: String,
                        @Key(TimetableConfig.EXPIRE_DATE)      expireDate: DateTime,
                        @Key(TimetableConfig.ELEMENT_TYPE_KEY) elementType: Int,
                        @Key(TimetableConfig.ELEMENT_ID_KEY)   elementId: Int,
                        @Key(TimetableConfig.USER_KEY)         userName: Option[String] = None,
                        @Key(TimetableConfig.PASSWORD_KEY)     password: Option[String] = None
                        )

object TimetableConfig{
  final val ID_KEY = "_id"
  final val USER_ID_KEY = "uid"
  final val DOCUMENT = "untisconfig"
  final val COOKIE_KEY = "cookie"
  final val EXPIRE_DATE = "expireDate"
  final val URL_KEY = "url"
  final val ELEMENT_TYPE_KEY = "elementType"
  final val ELEMENT_ID_KEY = "elementId"
  final val USER_KEY = "untisuser"
  final val PASSWORD_KEY = "untispassword"
}

case class Notification(
                         @Key(User.ID_KEY)  userId: ObjectId
                         )




