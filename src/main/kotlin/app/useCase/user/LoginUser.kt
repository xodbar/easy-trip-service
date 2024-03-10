package app.useCase.user

import app.core.auth.AuthService
import app.core.entity.user.service.UserService
import app.useCase.UseCase
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Component
class LoginUser(
  private val userService: UserService,
  private val authService: AuthService
) : UseCase<LoginUser.Input, LoginUser.Output>() {

  @Transactional
  override fun handle(input: Input): Output? {
    val user = userService.getExtendedByEmail(input.email)
      ?: throw RuntimeException("User with email [email=${input.email}] not found")

    if (!BCrypt.checkpw(input.password, user.password))
      throw RuntimeException("Password mismatch for user [${user.email}]")

    return Output(
      sessionId = authService.createSession(user.id)
    )
  }

  data class Input(
    val email: String,
    val password: String
  ) : Serializable

  data class Output(
    val sessionId: String
  ) : Serializable
}
