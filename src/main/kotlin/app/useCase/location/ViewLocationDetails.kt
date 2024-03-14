package app.useCase.location

import app.core.auth.AuthService
import app.core.entity.location.Location
import app.core.entity.location.LocationService
import app.core.entity.review.LocationReview
import app.core.entity.review.LocationReviewService
import app.core.entity.view.UserLocationViewService
import app.core.estateExplorer.dto.EstateExplorerEstateElement
import app.core.estateExplorer.dto.EstateExplorerEstateType
import app.core.estateExplorer.dto.EstateExplorerListRequest
import app.core.estateExplorer.dto.EstateExplorerPublicationType
import app.core.estateExplorer.service.EstateExplorerService
import app.useCase.UseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Component
class ViewLocationDetails(
  private val userLocationViewService: UserLocationViewService,
  private val locationReviewService: LocationReviewService,
  private val locationService: LocationService,
  private val authService: AuthService,
  private val estateExplorerService: EstateExplorerService
) : UseCase<ViewLocationDetails.Input, ViewLocationDetails.Output>() {

  @Transactional
  override fun handle(input: Input): Output? {
    val location = locationService.getById(input.locationId)
    val user = authService.getUserFromSession(input.sessionId)
      ?: throw RuntimeException("User not found in session")

    userLocationViewService.saveLocationViewByUser(
      userId = user.id,
      locationId = location.id,
      metadata = input.metadataJson
    )

    val estateList = estateExplorerService.getEstateList(
      EstateExplorerListRequest(
        city = location.nearestSupportedCity,
        estateType = EstateExplorerEstateType.APARTMENT,
        publicationType = EstateExplorerPublicationType.RENTING,
        params = null
      )
    )

    val reviews = locationReviewService.getReviewsByLocation(location.id)

    return Output(
      location = location,
      estateRecommendation = estateList.list,
      reviews = reviews
    )
  }

  data class Input(
    val sessionId: String,
    val locationId: Long,
    val metadataJson: String?
  ) : Serializable

  data class Output(
    val location: Location,
    val estateRecommendation: List<EstateExplorerEstateElement>,
    val reviews: List<LocationReview>
  ) : Serializable
}
