package app.useCase.location

import app.core.auth.AuthService
import app.core.entity.favorite.UserLocationFavoriteService
import app.core.entity.location.Location
import app.core.entity.location.LocationService
import app.core.entity.view.UserLocationViewService
import app.useCase.UseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Component
class GetLocationsList(
  private val locationService: LocationService,
  private val locationFavoriteService: UserLocationFavoriteService,
  private val locationViewService: UserLocationViewService,
  private val authService: AuthService
) : UseCase<GetLocationsList.Input, GetLocationsList.Output>() {

  @Transactional
  override fun handle(input: Input): Output? {
    val popular = locationService.getPopularLocations().map {
      locationService.getById(it.id)
    }

    var recentlyViewed: List<Location>? = null
    var favorite: List<Location>? = null
    if (input.sessionId != null) {
      val user = authService.getUserFromSession(input.sessionId)
        ?: throw RuntimeException("User not found by session")

      val favoriteLocations = locationFavoriteService.getAllByUser(user.id)
      favorite = favoriteLocations.map { locationService.getById(it.locationId) }

      val viewedLocations = locationViewService.getAllByUserId(user.id)
      recentlyViewed = viewedLocations.map { locationService.getById(it.locationId) }
    }

    return Output(
      popular = popular,
      favorite = favorite,
      recentlyViewed = recentlyViewed
    )
  }

  data class Input(
    val sessionId: String?
  ) : Serializable

  data class Output(
    val popular: List<Location>,
    var favorite: List<Location>?,
    var recentlyViewed: List<Location>?
  ) : Serializable
}
