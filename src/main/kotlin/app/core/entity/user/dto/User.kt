package app.core.entity.user.dto

import app.core.entity.subscription.SubscriptionCode
import app.core.entity.user.repo.UserStatus
import java.io.Serializable

data class User(
  val id: Long,
  val username: String,
  val fullName: UserFullName,
  val phoneNumber: String,
  val email: String,
  val status: UserStatus,
  val subscription: SubscriptionCode
) : Serializable {

  fun toBasic() = BasicUserInfo(
    id, username, fullName
  )
}

data class BasicUserInfo(
  val id: Long,
  val username: String,
  val fullName: UserFullName
)
