package app.core.entity.review

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationReviewRepo : JpaRepository<LocationReviewModel, Long> {
  fun findAllByLocationId(locationId: Long): List<LocationReviewModel>
}
