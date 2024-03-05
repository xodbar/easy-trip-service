package app.core.entity.location

import app.core.estateExplorer.dto.SupportedCity
import app.core.entity.tag.LocationTagModel
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
