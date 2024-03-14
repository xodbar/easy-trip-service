package app.core.entity.location.popular

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "popular_locations")
class PopularLocationModel(
  @Id
  val id: Long,
  val title: String,
  val description: String,
  val viewsCount: Long,
  val favoritesCount: Long,
  val averageRating: Double,
  val popularityScore: Long
) {
  fun toDTO() = PopularLocation(
    id = id,
    title = title,
    description = description,
    viewsCount = viewsCount,
    favoritesCount = favoritesCount,
    averageRating = averageRating,
    popularityScore = popularityScore
  )
}

data class PopularLocation(
  val id: Long,
  val title: String,
  val description: String,
  val viewsCount: Long,
  val favoritesCount: Long,
  val averageRating: Double,
  val popularityScore: Long
) : Serializable
