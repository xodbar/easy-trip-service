package app.core.entity.media.location

import app.core.entity.location.LocationModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "location_media")
class LocationMediaModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  val locationModel: LocationModel,

  @Column(name = "file_name", nullable = false, unique = true)
  val fileName: String,

  @Column(name = "bucket_name", nullable = false)
  val bucketName: String,

  @Column(name = "etag", nullable = false)
  val etag: String = "bucket_name",

  @field:CreationTimestamp
  val createdAt: LocalDateTime
) {

  fun toDTO() = LocationMedia(
    id = id,
    locationId = locationModel.id,
    fileName = fileName,
    bucketName = bucketName,
    createdAt = createdAt,
    etag = etag
  )
}
