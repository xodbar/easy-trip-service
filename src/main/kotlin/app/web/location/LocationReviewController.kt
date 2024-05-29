package app.web.location

import app.core.auth.AuthService
import app.core.entity.review.LocationReviewService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable

@RestController
@RequestMapping("/location-review")
@CrossOrigin
class LocationReviewController(
  private val locationReviewService: LocationReviewService,
  private val authService: AuthService
) {

  @PostMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  fun addReview(
    @RequestHeader(required = true) sessionId: String,
    @RequestBody request: LocationReviewRequest
  ) {
    val user = authService.getUserFromSession(sessionId)
      ?: throw RuntimeException("User with session $sessionId not found")

    locationReviewService.saveLocationReview(
      userId = user.id,
      locationId = request.locationId,
      content = request.content,
      rating = request.rating
    )
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class LocationReviewRequest(
  val content: String,
  val rating: Int,
  val locationId: Long
) : Serializable
