package app.infra.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig {

	@Bean
	fun jedisConnectionFactory() = JedisConnectionFactory()

	@Bean
	fun redisTemplate(): RedisTemplate<String, Any> {
		val template: RedisTemplate<String, Any> = RedisTemplate()
		template.connectionFactory = jedisConnectionFactory()

		return template
	}
}
