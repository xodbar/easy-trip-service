package app.core.entity.media.review

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationReviewMediaRepo : JpaRepository<LocationReviewMediaModel, Long> {
  fun findAllByLocationReviewId(locationReviewId: Long): List<LocationReviewMediaModel>
}
