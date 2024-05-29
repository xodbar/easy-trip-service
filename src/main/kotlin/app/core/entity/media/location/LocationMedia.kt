package app.core.entity.media.location

import java.io.Serializable
import java.time.LocalDateTime

data class LocationMedia(
  val id: Long,
  val locationId: Long,
  val fileName: String,
  val bucketName: String,
  val createdAt: LocalDateTime,
  val etag: String,
  val url: String? = null
) : Serializable
