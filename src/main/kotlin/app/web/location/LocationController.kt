package app.web.location

import app.useCase.location.get.GetLocationListInput
import app.useCase.location.get.GetLocationsList
import app.useCase.location.details.ViewLocationDetails
import app.useCase.location.details.ViewLocationDetailsInput
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/locations")
@CrossOrigin
class LocationController(
  private val viewLocationDetails: ViewLocationDetails,
  private val getLocationsList: GetLocationsList
) {

  @GetMapping
  @ResponseBody
  fun getLocations(@RequestHeader(required = false) sessionId: String?) =
    getLocationsList.handle(GetLocationListInput(sessionId))

  @GetMapping("/{id}")
  @ResponseBody
  fun getLocationDetails(
    @RequestHeader(required = true) sessionId: String,
    @PathVariable("id") locationId: Long
  ) = viewLocationDetails.handle(
    ViewLocationDetailsInput(
      sessionId = sessionId,
      locationId = locationId,
      metadataJson = null
    )
  )
}
