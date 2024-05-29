package app.useCase.user.login

import app.core.auth.AuthService
import app.core.entity.user.service.UserService
import app.useCase.UseCase
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class LoginUser(
  private val userService: UserService,
  private val authService: AuthService
) : UseCase<LoginUserInput, LoginUserOutput>() {

  @Transactional
  override fun handle(input: LoginUserInput): LoginUserOutput? {
    val user = userService.getExtendedByEmail(input.email)
      ?: userService.getExtendedByUsername(input.email)
      ?: throw RuntimeException("User with email [email=${input.email}] not found")

    if (!BCrypt.checkpw(input.password, user.password))
      throw RuntimeException("Password mismatch for user [${user.email}]")

    return LoginUserOutput(
      sessionId = authService.createSession(user.id)
    )
  }
}
