package app.core.entity.location

import app.core.entity.media.location.LocationMedia
import app.core.entity.tag.LocationTag
import app.core.estateExplorer.dto.SupportedCity
import java.io.Serializable
import java.time.LocalDateTime

open class Location(
  open val id: Long,
  open val title: String,
  open val description: String,
  open val coordinates: LocationCoordinate,
  open val nearestSupportedCity: SupportedCity,
  open val createdAt: LocalDateTime,
  open val updatedAt: LocalDateTime,
  open val tags: Set<LocationTag>,
  open val averageBudget: Double
) : Serializable

data class LocationWithMedia(
  val id: Long,
  val title: String,
  val description: String,
  val coordinates: LocationCoordinate,
  val nearestSupportedCity: SupportedCity,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val tags: Set<LocationTag>,
  val averageBudget: Double,
  val media: List<LocationMedia>,
  val averageRating: Double
) : Serializable
