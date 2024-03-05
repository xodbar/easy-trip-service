package app.core.minio

import io.minio.MinioClient
import io.minio.PutObjectArgs
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
}
