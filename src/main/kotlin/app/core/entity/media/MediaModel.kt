package app.core.entity.media

import app.core.utils.date.getCurrentAlmatyLocalDateTime
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class MediaModel {

  @Column(name = "file_name", nullable = false, unique = true)
  val fileName: String = "filename.jpeg"

  @Column(name = "bucket_name", nullable = false)
  val bucketName: String = "bucket_name"

  @field:CreationTimestamp
  val createdAt: LocalDateTime = getCurrentAlmatyLocalDateTime()
}
