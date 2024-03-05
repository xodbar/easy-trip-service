package app.core.entity.location

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class LocationCoordinate(

  @Column(name = "latitude")
  val latitude: Double,

  @Column(name = "longitude")
  val longitude: Double
) : Serializable
