package app.core.entity.tag

import jakarta.persistence.*

@Entity
@Table(name = "location_tags")
class LocationTagModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @Column(name = "name", nullable = false, unique = true)
  val name: String
)
