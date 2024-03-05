package app.core.cache

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class CacheService(
  private val redisTemplate: RedisTemplate<String, Any>,
  @Qualifier("redisObjectMapper") private val objectMapper: ObjectMapper,
  @Value("\${app.cache.expiration-millis}") private val defaultExpirationMillis: Long
) {

  fun putObject(key: String, obj: Any, expirationMillis: Long? = null) {
    val json = objectMapper.writeValueAsString(obj)
    redisTemplate.opsForValue()[key] = json
    redisTemplate.expire(key, expirationMillis ?: defaultExpirationMillis, TimeUnit.MILLISECONDS)
  }

  fun <T> getObject(key: String, clazz: Class<T>): T? {
    val json = redisTemplate.opsForValue()[key] as String? ?: return null
    return objectMapper.readValue(json, clazz)
  }
}
