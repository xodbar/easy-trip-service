package app.useCase.location.get

import java.io.Serializable

data class GetLocationListInput(
  val sessionId: String?
) : Serializable
