package app.core.entity.media.review

import app.core.entity.media.MediaModel
import app.core.entity.review.LocationReviewModel
import jakarta.persistence.*

@Entity
@Table(name = "location_review_media")
class LocationReviewMediaModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @ManyToOne
  @JoinColumn(name = "location_review_id", referencedColumnName = "id")
  val locationReview: LocationReviewModel
) : MediaModel()
