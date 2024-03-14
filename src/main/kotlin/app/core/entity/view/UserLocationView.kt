package app.core.entity.view

import java.io.Serializable
import java.time.LocalDateTime

class UserLocationView(
  val id: Long,
  val userId: Long,
  val locationId: Long,
  val timestamp: LocalDateTime,
  val metadataJson: String?
) : Serializable
