package app.web.location

import app.core.entity.location.LocationService
import app.useCase.location.GetLocationsList
import app.useCase.location.ViewLocationDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/locations")
class LocationController(
  private val viewLocationDetails: ViewLocationDetails,
  private val getLocationsList: GetLocationsList,
  private val locationService: LocationService
) {

  @GetMapping
  @ResponseBody
  fun getLocations(@RequestHeader(required = false) sessionId: String?) =
    getLocationsList.handle(GetLocationsList.Input(sessionId))

  @GetMapping("/{id}")
  @ResponseBody
  fun getLocationDetails(
    @RequestHeader(required = true) sessionId: String,
    @PathVariable("id") locationId: Long
  ) = viewLocationDetails.handle(
    ViewLocationDetails.Input(
      sessionId = sessionId,
      locationId = locationId,
      metadataJson = null
    )
  )
}
