package app.core.entity.location

import app.core.estateExplorer.dto.SupportedCity
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.io.Serializable

@Embeddable
data class LocationCoordinate(

	@Column(name = "latitude")
	val latitude: Double,

	@Column(name = "longitude")
	val longitude: Double
) : Serializable
