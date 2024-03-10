package app.core.entity.media.location

import app.core.entity.location.LocationModel
import app.core.entity.media.MediaModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "location_media")
class LocationMediaModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  val locationModel: LocationModel
) : MediaModel()
