package app.core.entity.review

import app.core.entity.location.LocationModel
import app.core.entity.user.repo.UserModel

class LocationReview(
  val id: Long,
  val userId: Long,
  val locationId: Long,
  val rating: Int,
  val content: String
) {
}
