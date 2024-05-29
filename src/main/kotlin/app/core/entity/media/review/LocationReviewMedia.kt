package app.core.entity.media.review

import java.io.Serializable
import java.time.LocalDateTime

data class LocationReviewMedia(
  val id: Long,
  val locationReviewId: Long,
  val fileName: String,
  val bucketName: String,
  val createdAt: LocalDateTime,
  val etag: String,
) : Serializable
