package app.useCase.location.details

import app.core.auth.AuthService
import app.core.entity.location.Location
import app.core.entity.location.LocationService
import app.core.entity.location.LocationWithMedia
import app.core.entity.media.location.LocationMediaService
import app.core.entity.media.review.LocationReviewMediaService
import app.core.entity.review.LocationReview
import app.core.entity.review.LocationReviewService
import app.core.entity.review.LocationReviewWithMedia
import app.core.entity.view.UserLocationViewService
import app.core.estateExplorer.dto.EstateExplorerEstateType
import app.core.estateExplorer.dto.EstateExplorerListRequest
import app.core.estateExplorer.dto.EstateExplorerPublicationType
import app.core.estateExplorer.service.EstateExplorerService
import app.useCase.UseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ViewLocationDetails(
  private val userLocationViewService: UserLocationViewService,
  private val locationReviewService: LocationReviewService,
  private val locationService: LocationService,
  private val locationMediaService: LocationMediaService,
  private val locationReviewMediaService: LocationReviewMediaService,
  private val authService: AuthService,
  private val estateExplorerService: EstateExplorerService
) : UseCase<ViewLocationDetailsInput, ViewLocationDetailsOutput>() {

  @Transactional
  override fun handle(input: ViewLocationDetailsInput): ViewLocationDetailsOutput? {
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

    return ViewLocationDetailsOutput(
      location = mapToLocationWithMedia(location),
      estateRecommendation = estateList.list,
      reviews = reviews.map { mapToReviewWithMedia(it) }
    )
  }

  private fun mapToLocationWithMedia(location: Location) = LocationWithMedia(
    id = location.id,
    title = location.title,
    description = location.description,
    coordinates = location.coordinates,
    nearestSupportedCity = location.nearestSupportedCity,
    createdAt = location.createdAt,
    updatedAt = location.updatedAt,
    tags = location.tags,
    averageBudget = location.averageBudget,
    media = locationMediaService.getAllByLocationId(location.id),
    averageRating = locationReviewService.averageRating(location.id)
  )

  private fun mapToReviewWithMedia(locationReview: LocationReview) = LocationReviewWithMedia(
    id = locationReview.id,
    userId = locationReview.userId,
    locationId = locationReview.locationId,
    rating = locationReview.rating,
    content = locationReview.content,
    media = locationReviewMediaService.getAllByLocationReview(locationReview.id)
  )
}
