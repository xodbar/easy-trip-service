package app.core.entity.view

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserLocationViewRepo : JpaRepository<UserLocationViewModel, Long> {
  fun findAllByUserId(userId: Long): List<UserLocationViewModel>
}
