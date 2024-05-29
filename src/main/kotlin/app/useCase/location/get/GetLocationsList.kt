package app.useCase.location.get

import app.core.auth.AuthService
import app.core.entity.favorite.UserLocationFavoriteService
import app.core.entity.location.Location
import app.core.entity.location.LocationService
import app.core.entity.location.LocationWithMedia
import app.core.entity.media.location.LocationMediaService
import app.core.entity.review.LocationReviewService
import app.core.entity.view.UserLocationViewService
import app.useCase.UseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class GetLocationsList(
  private val locationService: LocationService,
  private val locationFavoriteService: UserLocationFavoriteService,
  private val locationViewService: UserLocationViewService,
  private val locationMediaService: LocationMediaService,
  private val locationReviewService: LocationReviewService,
  private val authService: AuthService
) : UseCase<GetLocationListInput, GetLocationsListOutput>() {

  @Transactional
  override fun handle(input: GetLocationListInput): GetLocationsListOutput? {
    val popular = locationService.getAll()
      .map { locationService.getById(it.id) }
      .map { mapToWithMedia(it) }

    var recentlyViewed: List<LocationWithMedia>? = null
    var favorite: List<LocationWithMedia>? = null
    if (input.sessionId != null) {
      val user = authService.getUserFromSession(input.sessionId)
        ?: throw RuntimeException("User not found by session")

      val favoriteLocations = locationFavoriteService.getAllByUser(user.id)
      favorite = favoriteLocations
        .map { locationService.getById(it.locationId) }
        .map { mapToWithMedia(it) }

      val viewedLocations = locationViewService.getAllByUserId(user.id)
      recentlyViewed = viewedLocations
        .distinctBy { it.locationId }
        .map { locationService.getById(it.locationId) }
        .map { mapToWithMedia(it) }
    }

    return GetLocationsListOutput(
      popular = popular,
      favorite = favorite,
      recentlyViewed = recentlyViewed
    )
  }

  private fun mapToWithMedia(location: Location) = LocationWithMedia(
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
}
