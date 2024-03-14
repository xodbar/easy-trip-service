package app.core.entity.favorite

import app.core.entity.location.LocationRepo
import app.core.entity.user.repo.UserRepo
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.stereotype.Service

@Service
class UserLocationFavoriteService(
  private val userLocationFavoriteRepo: UserLocationFavoriteRepo,
  private val userRepo: UserRepo,
  private val locationRepo: LocationRepo
) {

  fun saveFavoriteLocation(userId: Long, locationId: Long, metadataJson: String?): UserLocationFavorite {
    val (user, location) = Pair(
      userRepo.findById(userId).orElseThrow(),
      locationRepo.findById(locationId).orElseThrow()
    )

    return userLocationFavoriteRepo.save(
      UserLocationFavoriteModel(
        user = user,
        location = location,
        metadataJson = metadataJson,
        timestamp = getCurrentAlmatyLocalDateTime()
      )
    ).toDTO()
  }

  fun getAllByUser(userId: Long) =
    userLocationFavoriteRepo.findAllByUserId(userId).map { it.toDTO() }
}

