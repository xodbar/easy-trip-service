package app.core.minio

import io.minio.GetPresignedObjectUrlArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.http.Method
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MinioService(
  private val minioClient: MinioClient,
  @Value("\${app.minio.default-bucket}") private val defaultBucketName: String
) {

  fun upload(filename: String, file: ByteArray, bucketName: String? = null): String = minioClient.putObject(
    PutObjectArgs.builder()
      .bucket(bucketName ?: defaultBucketName)
      .`object`(filename)
      .stream(file.inputStream(), file.size.toLong(), 0)
      .build()
  ).etag()

  fun getFileUrl(filename: String, bucketName: String? = null): String {
    val expiryTime = 60 * 60 * 24 * 7 // URL будет действителен на 7 дней
    return minioClient.getPresignedObjectUrl(
      GetPresignedObjectUrlArgs.builder()
        .method(Method.GET)
        .bucket(bucketName ?: defaultBucketName)
        .`object`(filename)
        .expiry(expiryTime)
        .build()
    )
  }
}
