package app.web.admin

import app.core.entity.tag.LocationTagService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/location-tags")
@CrossOrigin
class LocationTagAdminController(
  private val locationTagService: LocationTagService
) {

  @GetMapping
  @ResponseBody
  fun getLocationTags() =
    locationTagService.getAllTags()

  @PostMapping
  @ResponseBody
  fun saveLocationTag(@RequestParam name: String) =
    locationTagService.addTag(name)
}
