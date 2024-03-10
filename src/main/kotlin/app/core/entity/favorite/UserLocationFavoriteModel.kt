package app.core.entity.favorite

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
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime


@Entity
@Table(name = "users_locations_favorite")
@DynamicUpdate
class UserLocationFavoriteModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  val user: UserModel,

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  val location: LocationModel,

  @CreationTimestamp
  val timestamp: LocalDateTime,

  @Column(name = "metadata")
  val metadataJson: String?
)
