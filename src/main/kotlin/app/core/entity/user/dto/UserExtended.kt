package app.core.entity.user.dto

import app.core.entity.subscription.SubscriptionCode
import app.core.entity.user.repo.UserStatus
import java.io.Serializable
import java.time.LocalDateTime

data class UserExtended(
  val id: Long,
  val username: String,
  val password: String,
  val fullName: UserFullName,
  val phoneNumber: String,
  val email: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val status: UserStatus,
  val subscription: SubscriptionCode
) : Serializable
