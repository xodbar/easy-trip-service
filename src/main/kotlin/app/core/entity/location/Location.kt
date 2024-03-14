package app.core.entity.location

import app.core.entity.tag.LocationTag
import app.core.estateExplorer.dto.SupportedCity
import java.io.Serializable
import java.time.LocalDateTime

data class Location(
  val id: Long,
  val title: String,
  val description: String,
  val coordinates: LocationCoordinate,
  val nearestSupportedCity: SupportedCity,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val tags: Set<LocationTag>,
  val averageBudget: Double
) : Serializable
