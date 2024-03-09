package app.core.entity.user.repo

import app.core.entity.subscription.SubscriptionModel
import app.core.entity.user.dto.UserFullName
import app.core.entity.user.dto.User
import app.core.entity.user.dto.UserExtended
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@DynamicUpdate
class UserModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(name = "username", unique = true, nullable = false)
  var username: String,

  @Column(name = "password", nullable = false)
  var password: String,

  @Embedded
  var fullName: UserFullName,

  @Column(name = "phone", unique = true)
  var phoneNumber: String,

  @Column(name = "email", unique = true)
  var email: String,

  @CreationTimestamp
  val createdAt: LocalDateTime,

  @UpdateTimestamp
  val updatedAt: LocalDateTime,

  @Enumerated(EnumType.STRING)
  var status: UserStatus,

  @ManyToOne
  @JoinColumn(name = "subscription_id")
  var subscription: SubscriptionModel
) {

  fun toDTO() = User(
    id = id,
    username = username,
    fullName = fullName,
    phoneNumber = phoneNumber,
    email = email,
    status = status,
    subscription = subscription.code
  )

  fun toExtendedDTO() = UserExtended(
    id = id,
    username = username,
    fullName = fullName,
    phoneNumber = phoneNumber,
    email = email,
    status = status,
    subscription = subscription.code,
    password = password,
    createdAt = createdAt,
    updatedAt = updatedAt
  )
}

enum class UserStatus(val description: String) {
  ACTIVE("Active"),
  BLOCKED_DUE_TO_POLICY("Blocked due to app policy"),
  BLOCKED_DUE_TO_INACTIVITY("Blocked due to inactivity")
}
