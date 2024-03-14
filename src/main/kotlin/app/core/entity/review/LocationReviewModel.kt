package app.core.entity.review

import app.core.entity.location.LocationModel
import app.core.entity.user.repo.UserModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "location_review")
class LocationReviewModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  val user: UserModel,

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  val location: LocationModel,

  @Column(name = "rating", nullable = false)
  val rating: Int,

  @Column(name = "content")
  val content: String
) {
  fun toDTO() = LocationReview(
    id = id,
    userId = user.id,
    locationId = location.id,
    rating = rating,
    content = content,
  )
}
