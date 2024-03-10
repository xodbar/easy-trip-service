package app.web.admin

import app.core.entity.subscription.SubscriptionCode
import app.core.entity.user.dto.User
import app.core.entity.user.dto.UserFullName
import app.core.entity.user.repo.UserStatus
import app.core.entity.user.service.UserService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable

@RestController
@RequestMapping("/admin/users")
@CrossOrigin
class UserAdminController(
  private val userService: UserService
) {

  @GetMapping
  @ResponseBody
  fun list(): List<User> = userService.getAllUsers()

  @GetMapping("/{id}")
  fun detail(@PathVariable id: Long): ResponseEntity<User> =
    userService.getById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

  @PostMapping
  fun create(@RequestBody request: SaveUserRequest): ResponseEntity<User> =
    ResponseEntity.ok(
      userService.createUser(
        username = request.username,
        phone = request.phone,
        email = request.email,
        fullName = request.fullName,
        subscriptionCode = request.subscriptionCode,
        password = request.password
      )
    )

  @PutMapping("/{id}")
  fun update(@PathVariable id: Long, @RequestBody request: SaveUserRequest): ResponseEntity<User> {
    val user = userService.getById(id) ?: return ResponseEntity.notFound().build()
    return ResponseEntity.ok(
      userService.updateUser(
        id = user.id,
        username = request.username,
        password = request.password,
        fullName = request.fullName,
        phoneNumber = request.phone,
        email = request.email,
        status = request.status ?: user.status
      )
    )
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaveUserRequest(
  val username: String,
  val phone: String,
  val email: String,
  val fullName: UserFullName,
  val subscriptionCode: SubscriptionCode,
  val password: String,
  val status: UserStatus?
) : Serializable
