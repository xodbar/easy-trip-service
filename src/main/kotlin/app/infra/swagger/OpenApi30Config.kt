package app.infra.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
  info = Info(
    title = "easy-trip-service",
    version = "v1.0 BETA",
    contact = Contact(
      name = "IITU", email = "29316@edu.iitu.kz", url = "https://iitu.kz"
    ),
    license = License(
      name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
    ),
    termsOfService = "/health",
    description = "API of Easy-Trip application"
  )
)
class OpenApi30Config
