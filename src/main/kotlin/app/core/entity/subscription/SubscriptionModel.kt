package app.core.entity.subscription

import jakarta.persistence.*

@Entity
@Table(name = "dict_subscriptions")
class SubscriptionModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Enumerated(EnumType.STRING)
  val code: SubscriptionCode,

  @Column(name = "price", nullable = false)
  val price: Long,

  @Column(name = "description", columnDefinition = "text", unique = true, nullable = false)
  val description: String
)

enum class SubscriptionCode {
  BASE
}
