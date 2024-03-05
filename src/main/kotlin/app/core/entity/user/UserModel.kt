package app.core.entity.user

import app.core.entity.subscription.SubscriptionModel
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
import java.time.LocalDateTime
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "users")
class UserModel(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name = "username", unique = true, nullable = false)
	var username: String,

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
	val status: UserStatus,

	@ManyToOne
	@JoinColumn(name = "subscription_id")
	val subscription: SubscriptionModel
)

enum class UserStatus(val description: String) {
	ACTIVE("Active"),
	BLOCKED_DUE_TO_POLICY("Blocked due to app policy"),
	BLOCKED_DUE_TO_INACTIVITY("Blocked due to inactivity")
}
