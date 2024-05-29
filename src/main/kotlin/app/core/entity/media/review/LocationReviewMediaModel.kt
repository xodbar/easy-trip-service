package app.core.entity.media.review

import app.core.entity.review.LocationReviewModel
import app.core.utils.date.getCurrentAlmatyLocalDateTime
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
@Table(name = "location_review_media")
class LocationReviewMediaModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @ManyToOne
  @JoinColumn(name = "location_review_id", referencedColumnName = "id")
  val locationReview: LocationReviewModel,

  @Column(name = "file_name", nullable = false, unique = true)
  val fileName: String = "filename.jpeg",

  @Column(name = "bucket_name", nullable = false)
  val bucketName: String = "bucket_name",

  @Column(name = "etag", nullable = false)
  val etag: String = "bucket_name",

  @field:CreationTimestamp
  val createdAt: LocalDateTime = getCurrentAlmatyLocalDateTime()
) {

  fun toDTO() = LocationReviewMedia(
    id = id,
    locationReviewId = locationReview.id,
    fileName = fileName,
    bucketName = bucketName,
    createdAt = createdAt,
    etag = etag
  )
}
