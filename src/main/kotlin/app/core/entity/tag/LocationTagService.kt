package app.core.entity.tag

import org.springframework.stereotype.Service

@Service
class LocationTagService(
  private val locationTagRepo: LocationTagRepo
) {

  fun getAllTags() =
    locationTagRepo.findAll().map { it.toDTO() }

  fun addTag(name: String) =
    locationTagRepo.save(
      LocationTagModel(
        id = -1,
        name = name
      )
    ).toDTO()
}
