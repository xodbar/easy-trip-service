package app.useCase.location.details

import java.io.Serializable

data class ViewLocationDetailsInput(
  val sessionId: String,
  val locationId: Long,
  val metadataJson: String?
) : Serializable
