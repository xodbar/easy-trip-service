package app.core.entity.location

import app.core.entity.location.popular.PopularLocationRepo
import app.core.entity.tag.LocationTag
import app.core.entity.tag.LocationTagRepo
import app.core.estateExplorer.dto.SupportedCity
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
class LocationService(
  private val repo: LocationRepo,
  private val locationTagRepo: LocationTagRepo,
  private val popularLocationRepo: PopularLocationRepo
) {

  fun getAll() =
    repo.findAll().map { it.toDTO() }

  fun createLocation(
    title: String,
    description: String,
    coordinates: LocationCoordinate,
    nearestSupportedCity: SupportedCity,
    tags: Set<LocationTag>,
    averageBudget: Double
  ) = repo.save(
    LocationModel(
      title = title,
      description = description,
      coordinates = coordinates,
      nearestSupportedCity = nearestSupportedCity,
      createdAt = getCurrentAlmatyLocalDateTime(),
      updatedAt = getCurrentAlmatyLocalDateTime(),
      tags = locationTagRepo.getAllByNameIn(tags.map { it.name }).toSet(),
      averageBudget = averageBudget
    )
  ).toDTO()

  fun getLocationByTitle(title: String) =
    repo.findByTitle(title)?.toDTO()

  fun updateLocation(id: Long, updateLocationRequest: UpdateLocationRequest) {
    val model = repo.findById(id).get()

    updateLocationRequest.title?.let { model.title = it }
    updateLocationRequest.description?.let { model.description = it }
    updateLocationRequest.coordinates?.let { model.coordinates = it }
    updateLocationRequest.nearestSupportedCity?.let { model.nearestSupportedCity = it }
    updateLocationRequest.tags?.let { model.tags = locationTagRepo.getAllByNameIn(it.map { tag -> tag.name }).toSet() }
    updateLocationRequest.averageBudget?.let { model.averageBudget = it }

    repo.save(model)
  }

  fun getPopularLocations() =
    popularLocationRepo.findAll()
      .map { it.toDTO() }
      .sortedByDescending { it.viewsCount }

  fun getById(id: Long) =
    repo.findById(id).orElseThrow().toDTO()
}

data class UpdateLocationRequest(
  val title: String?,
  val description: String?,
  val coordinates: LocationCoordinate?,
  val nearestSupportedCity: SupportedCity?,
  val tags: Set<LocationTag>?,
  val averageBudget: Double?
) : Serializable
