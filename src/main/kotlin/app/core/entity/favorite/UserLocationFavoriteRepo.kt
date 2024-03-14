package app.core.entity.favorite

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserLocationFavoriteRepo : JpaRepository<UserLocationFavoriteModel, Long> {
  fun findAllByUserId(userId: Long): List<UserLocationFavoriteModel>
  fun countAllByLocationId(locationId: Long): Long
}
