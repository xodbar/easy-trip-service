package app.core.entity.review

import app.core.entity.media.review.LocationReviewMedia
import java.io.Serializable

open class LocationReview(
  open val id: Long,
  open val userId: Long,
  open val locationId: Long,
  open val rating: Int,
  open val content: String
) : Serializable

data class LocationReviewWithMedia(
  override val id: Long,
  override val userId: Long,
  override val locationId: Long,
  override val rating: Int,
  override val content: String,
  val media: List<LocationReviewMedia>
) : LocationReview(id, userId, locationId, rating, content)
