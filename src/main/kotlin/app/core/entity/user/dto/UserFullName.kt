package app.core.entity.user.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
data class UserFullName(

  @Column(name = "last_name", nullable = false)
  val lastName: String,

  @Column(name = "first_name", nullable = false)
  val firstName: String,

  @Column(name = "middle_name")
  val middleName: String?
) : Serializable
