package app.core.entity.location

import app.core.entity.tag.LocationTagModel
import app.core.estateExplorer.dto.SupportedCity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "locations")
@DynamicUpdate
class LocationModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(name = "title", unique = true, nullable = false)
  var title: String,

  @Column(name = "description", columnDefinition = "text", unique = true, nullable = false)
  var description: String,

  @Embedded
  var coordinates: LocationCoordinate,

  @Enumerated(EnumType.STRING)
  var nearestSupportedCity: SupportedCity,

  @CreationTimestamp
  var createdAt: LocalDateTime,

  @UpdateTimestamp
  var updatedAt: LocalDateTime,

  @ManyToMany
  @JoinTable(
    name = "locations_tags",
    joinColumns = [JoinColumn(name = "location_id")],
    inverseJoinColumns = [JoinColumn(name = "tag_id")]
  )
  var tags: Set<LocationTagModel>
) {

  fun toDTO() = Location(
    id = id,
    title = title,
    description = description,
    coordinates = coordinates,
    nearestSupportedCity = nearestSupportedCity,
    createdAt = createdAt,
    updatedAt = updatedAt,
    tags = tags.map { it.toDTO() }.toSet(),
  )
}
