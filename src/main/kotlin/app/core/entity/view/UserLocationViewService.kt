package app.core.entity.view

import app.core.entity.location.LocationRepo
import app.core.entity.user.repo.UserRepo
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.stereotype.Service

@Service
class UserLocationViewService(
  private val userLocationViewRepo: UserLocationViewRepo,
  private val locationRepo: LocationRepo,
  private val userRepo: UserRepo
) {

  fun saveLocationViewByUser(userId: Long, locationId: Long, metadata: String?): UserLocationView {
    val user = userRepo.findById(userId).orElseThrow()
    val location = locationRepo.findById(locationId).orElseThrow()

    return userLocationViewRepo.save(
      UserLocationViewModel(
        id = 0,
        user = user,
        location = location,
        timestamp = getCurrentAlmatyLocalDateTime(),
        metadataJson = metadata
      )
    ).toDTO()
  }

  fun getAllByUserId(userId: Long) =
    userLocationViewRepo.findAllByUserId(userId).map { it.toDTO() }
}
