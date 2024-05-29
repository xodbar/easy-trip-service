package app.useCase.user.loginViaEmail

import java.io.Serializable

data class LoginUserViaEmailInput(
  val uuid: String
) : Serializable
