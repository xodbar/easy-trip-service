package app.core.entity.media.location

import app.core.entity.location.LocationRepo
import app.core.minio.MinioService
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LocationMediaService(
  private val repo: LocationMediaRepo,
  private val locationRepo: LocationRepo,
  private val minioService: MinioService
) {

  @Transactional
  fun saveLocationMedia(filename: String, bucketName: String, locationId: Long, etag: String): LocationMedia {
    val locationModel = locationRepo.findById(locationId).get()
    return repo.save(
      LocationMediaModel(
        id = -1,
        locationModel = locationModel,
        fileName = filename,
        bucketName = bucketName,
        createdAt = getCurrentAlmatyLocalDateTime()
      )
    ).toDTO().copy(url = minioService.getFileUrl(filename))
  }

  @Transactional
  fun getAllByLocationId(locationId: Long) =
    repo.findAllByLocationModelId(locationId)
      .map { it.toDTO() }
      .map { it.copy(url = minioService.getFileUrl(it.fileName)) }
}
