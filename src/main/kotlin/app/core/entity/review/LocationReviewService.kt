package app.core.entity.review

import org.springframework.stereotype.Service

@Service
class LocationReviewService(
  private val locationReviewRepo: LocationReviewRepo
) {

  fun saveLocationReview(userId: Long, locationId: Long, content: String) {

  }

  fun getReviewsByLocation(locationId: Long): List<LocationReview> =
    locationReviewRepo.findAllByLocationId(locationId).map { it.toDTO() }
}
