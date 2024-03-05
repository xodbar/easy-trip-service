package app.core.entity.tag

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "location_tags")
class LocationTagModel(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long,

	@Column(name = "name", nullable = false, unique = true)
	val name: String
)
