package app.core.entity.subscription

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepo : JpaRepository<SubscriptionModel, Long> {
  fun findByCode(code: SubscriptionCode): SubscriptionModel?
}
