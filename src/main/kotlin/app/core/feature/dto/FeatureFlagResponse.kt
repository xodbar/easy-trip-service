package app.core.feature.dto

import app.core.feature.FeatureName
import app.core.feature.FeatureState

internal data class FeatureFlagResponse(
  val name: FeatureName,
  val description: String,
  val state: FeatureState
)
