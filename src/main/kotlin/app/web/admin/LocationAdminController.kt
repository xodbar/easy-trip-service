package app.web.admin

import app.core.entity.location.LocationCoordinate
import app.core.entity.location.LocationService
import app.core.entity.location.UpdateLocationRequest
import app.core.entity.tag.LocationTag
import app.core.estateExplorer.dto.SupportedCity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable

@RestController
@RequestMapping("/admin/locations")
@CrossOrigin
class LocationAdminController(
  private val locationService: LocationService
) {

  @GetMapping
  @ResponseBody
  fun getAllLocations() =
    locationService.getAll()

  @PostMapping
  @ResponseBody
  fun createLocation(@RequestBody request: CreateLocationRequest) =
    locationService.createLocation(
      title = request.title,
      description = request.description,
      coordinates = request.coordinates,
      nearestSupportedCity = request.nearestSupportedCity,
      tags = request.tags,
      averageBudget = request.averageBudget
    )

  @GetMapping("/{id}")
  @ResponseBody
  fun getLocationDetails(@PathVariable id: Long) =
    locationService.getById(id)

  @PutMapping("/{id}")
  @ResponseBody
  fun updateLocation(
    @PathVariable id: Long,
    @RequestBody request: UpdateLocationRequest
  ) {
    locationService.updateLocation(id, request)
  }
}

data class CreateLocationRequest(
  val title: String,
  val description: String,
  val coordinates: LocationCoordinate,
  val nearestSupportedCity: SupportedCity,
  val tags: Set<LocationTag>,
  val averageBudget: Double
) : Serializable
