package app.infra.serialization

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SerializationConfig {

	@Bean
	fun redisObjectMapper(): ObjectMapper {
		val timeModule = JavaTimeModule()
		timeModule.addSerializer(LocalDateSerializer.INSTANCE)

		return ObjectMapper()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.registerModules(timeModule)
	}
}
