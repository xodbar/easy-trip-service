package app.useCase.user.loginViaEmail

import app.core.auth.AuthService
import app.core.cache.CacheService
import app.core.entity.user.repo.UserStatus
import app.core.entity.user.service.UserService
import app.useCase.UseCase
import app.useCase.user.login.LoginUserOutput
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Component
class LoginViaEmail(
  private val userService: UserService,
  private val cacheService: CacheService,
  private val authService: AuthService
) : UseCase<LoginUserViaEmailInput, LoginUserOutput>() {

  @Transactional
  override fun handle(input: LoginUserViaEmailInput): LoginUserOutput {
    val session = cacheService.getObject(input.uuid, LoginViaEmailElement::class.java)
      ?: throw RuntimeException("Cache with [uuid=${input.uuid}] is not exists")

    val user = userService.getByUsername(session.username)
      ?: throw RuntimeException("User with [username=${session.username}] is not found")

    if (user.status == UserStatus.VERIFICATION_PENDING)
      userService.updateUser(
        id = user.id,
        status = UserStatus.ACTIVE
      )

    return LoginUserOutput(authService.createSession(user.id))
  }
}

data class LoginViaEmailElement(
  val username: String
) : Serializable
