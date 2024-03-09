package app.core.entity.location

import app.core.entity.tag.LocationTag
import app.core.entity.tag.LocationTagRepo
import app.core.estateExplorer.dto.SupportedCity
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.stereotype.Service

@Service
class LocationService(
  private val repo: LocationRepo,
  private val locationTagRepo: LocationTagRepo
) {

  fun createLocation(
    title: String,
    description: String,
    coordinates: LocationCoordinate,
    nearestSupportedCity: SupportedCity,
    tags: Set<LocationTag>
  ) = repo.save(
    LocationModel(
      title = title,
      description = description,
      coordinates = coordinates,
      nearestSupportedCity = nearestSupportedCity,
      createdAt = getCurrentAlmatyLocalDateTime(),
      updatedAt = getCurrentAlmatyLocalDateTime(),
      tags = locationTagRepo.getAllByNameIn(tags.map { it.name }).toSet()
    )
  ).toDTO()

  fun getLocationByTitle(title: String) =
    repo.findFirstByTitle(title)?.toDTO()
}
