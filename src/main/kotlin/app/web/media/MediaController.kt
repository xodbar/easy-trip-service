package app.web.media

import app.core.entity.media.location.LocationMediaService
import app.core.entity.media.review.LocationReviewMediaService
import app.core.minio.MinioService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/media")
@CrossOrigin
class MediaController(
  private val locationMediaService: LocationMediaService,
  private val locationReviewMediaService: LocationReviewMediaService,
  private val minioService: MinioService,
  @Value("\${app.minio.default-bucket}") private val bucketName: String
) {

  @PostMapping("/upload-location-media")
  @ResponseStatus(HttpStatus.ACCEPTED)
  fun uploadLocationMedia(
    @RequestParam("file") file: MultipartFile,
    @RequestParam("filename") filename: String,
    @RequestParam("location_id") locationId: Long
  ) {
    val etag = minioService.upload(
      filename = filename,
      bucketName = bucketName,
      file = file.bytes
    )

    locationMediaService.saveLocationMedia(
      filename = filename,
      bucketName = bucketName,
      locationId = locationId,
      etag = etag
    )
  }

  @PostMapping("/upload-review-media")
  @ResponseStatus(HttpStatus.ACCEPTED)
  fun uploadLocationReviewMedia(
    @RequestParam("file") file: MultipartFile,
    @RequestParam("filename") filename: String,
    @RequestParam("location_review_id") locationReviewId: Long
  ) {
    val etag = minioService.upload(
      filename = filename,
      bucketName = bucketName,
      file = file.bytes
    )

    locationReviewMediaService.saveLocationReviewMedia(
      filename = filename,
      bucketName = bucketName,
      locationReviewId = locationReviewId,
      etag = etag
    )
  }
}
