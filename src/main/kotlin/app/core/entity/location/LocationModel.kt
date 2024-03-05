package app.core.entity.location

import app.core.entity.tag.LocationTagModel
import app.core.estateExplorer.dto.SupportedCity
import jakarta.persistence.*

@Entity
@Table(name = "locations")
class LocationModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(name = "title", unique = true, nullable = false)
  val title: String,

  @Column(name = "description", columnDefinition = "text", unique = true, nullable = false)
  val description: String,

  @Embedded
  val coordinates: LocationCoordinate,

  @Enumerated(EnumType.STRING)
  val nearestSupportedCity: SupportedCity,

  @ManyToMany
  @JoinTable(
    name = "locations_tags",
    joinColumns = [JoinColumn(name = "location_id")],
    inverseJoinColumns = [JoinColumn(name = "tag_id")]
  )
  val tags: Set<LocationTagModel>
)
