package app.web.mvc

import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class MvcController(
  private val env: Environment
) {

  @GetMapping
  fun index(): ResponseEntity<Map<String, String>> {
    val headers = HttpHeaders()
    headers.add(
      "Location",
      "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}/html/index.html"
    )

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build()
  }

  @GetMapping("/auth-login")
  fun authLogin(): ResponseEntity<Map<String, String>> {
    val headers = HttpHeaders()
    headers.add(
      "Location",
      "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}/html/auth-login.html"
    )

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build()
  }

  @GetMapping("/auth-registration")
  fun authRegistration(): ResponseEntity<Map<String, String>> {
    val headers = HttpHeaders()
    headers.add(
      "Location",
      "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}/html/auth-registration.html"
    )

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build()
  }

  @GetMapping("/auth-forget-password")
  fun authForgetPassword(): ResponseEntity<Map<String, String>> {
    val headers = HttpHeaders()
    headers.add(
      "Location",
      "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}/html/auth-forget-password.html"
    )

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build()
  }

  @GetMapping("/ui-locations")
  fun locations(): ResponseEntity<Map<String, String>> {
    val headers = HttpHeaders()
    headers.add(
      "Location",
      "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}/html/ui-home.html"
    )

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build()
  }
}
