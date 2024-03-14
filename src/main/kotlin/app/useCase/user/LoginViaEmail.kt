package app.useCase.user

import app.core.auth.AuthService
import app.core.cache.CacheService
import app.core.entity.user.service.UserService
import app.useCase.UseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Component
class LoginViaEmail(
  private val userService: UserService,
  private val cacheService: CacheService,
  private val authService: AuthService
) : UseCase<LoginViaEmail.Input, LoginUser.Output>() {

  data class Input(
    val uuid: String
  ) : Serializable

  @Transactional
  override fun handle(input: Input): LoginUser.Output {
    val session = cacheService.getObject(input.uuid, LoginViaEmailElement::class.java)
      ?: throw RuntimeException("Cache with [uuid=${input.uuid}] is not exists")

    val user = userService.getByUsername(session.username)
      ?: throw java.lang.RuntimeException("User with [username=${session.username}] is not found")

    return LoginUser.Output(authService.createSession(user.id))
  }
}

data class LoginViaEmailElement(
  val username: String
) : Serializable
