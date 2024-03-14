package app.core.entity.favorite

import java.io.Serializable
import java.time.LocalDateTime

data class UserLocationFavorite(
  val userId: Long,
  val locationId: Long,
  val timestamp: LocalDateTime
) : Serializable
