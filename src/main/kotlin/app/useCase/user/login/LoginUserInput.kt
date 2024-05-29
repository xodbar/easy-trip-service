package app.useCase.user.login

import java.io.Serializable

data class LoginUserInput(
  val email: String,
  val password: String
) : Serializable
