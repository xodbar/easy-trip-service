package app.useCase.user.login

import java.io.Serializable

data class LoginUserOutput(
  val sessionId: String
) : Serializable
