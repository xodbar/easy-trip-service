package app.core.entity.review

import app.core.entity.location.LocationRepo
import app.core.entity.user.repo.UserRepo
import org.springframework.stereotype.Service

@Service
class LocationReviewService(
  private val locationReviewRepo: LocationReviewRepo,
  private val locationRepo: LocationRepo,
  private val userRepo: UserRepo
) {

  fun saveLocationReview(userId: Long, locationId: Long, content: String, rating: Int): LocationReview {
    val user = userRepo.findById(userId).get()
    val location = locationRepo.findById(locationId).get()

    return locationReviewRepo.save(
      LocationReviewModel(
        id = -1,
        user = user,
        location = location,
        rating = rating,
        content = content
      )
    ).toDTO()
  }

  fun getReviewsByLocation(locationId: Long): List<LocationReview> =
    locationReviewRepo.findAllByLocationId(locationId).map { it.toDTO() }

  fun averageRating(locationId: Long): Double {
    val ratings = locationReviewRepo.findAllByLocationId(locationId).map { it.rating }
    if (ratings.isEmpty()) return 0.0

    return ratings.average()
  }
}
