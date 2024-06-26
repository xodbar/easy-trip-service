package app.core.cache

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class CacheService(
  private val redisTemplate: RedisTemplate<String, Any>,
  @Value("\${app.cache.expiration-millis}") private val defaultExpirationMillis: Long
) {

  fun putObject(key: String, obj: Any, expirationMillis: Long? = null) {
    val json = jacksonObjectMapper().registerKotlinModule().registerModule(JavaTimeModule()).writeValueAsString(obj)
    redisTemplate.opsForValue()[key] = json
    redisTemplate.expire(key, expirationMillis ?: defaultExpirationMillis, TimeUnit.MILLISECONDS)
  }

  fun <T> getObject(key: String, clazz: Class<T>): T? {
    val json = redisTemplate.opsForValue()[key] as String? ?: return null
    return jacksonObjectMapper().registerKotlinModule().registerModule(JavaTimeModule()).readValue(json, clazz)
  }
}
