package app.core.entity.media.review

import app.core.entity.review.LocationReviewRepo
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LocationReviewMediaService(
  private val repo: LocationReviewMediaRepo,
  private val locationReviewRepo: LocationReviewRepo
) {

  @Transactional
  fun saveLocationReviewMedia(filename: String, bucketName: String, locationReviewId: Long, etag: String): LocationReviewMedia {
    val locationReviewModel = locationReviewRepo.findById(locationReviewId).get()
    return repo.save(
      LocationReviewMediaModel(
        id = -1,
        locationReview = locationReviewModel,
        fileName = filename,
        bucketName = bucketName,
        createdAt = getCurrentAlmatyLocalDateTime(),
        etag = etag
      )
    ).toDTO()
  }

  @Transactional
  fun getAllByLocationReview(locationReviewId: Long) =
    repo.findAllByLocationReviewId(locationReviewId).map { it.toDTO() }
}
