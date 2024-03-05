package app.core.feature.service

import app.core.feature.FeatureName
import app.core.feature.FeatureState

internal interface FeatureFlagService {
  fun getAll(): Map<FeatureName, FeatureState>
  fun get(featureName: FeatureName): FeatureState
  fun set(featureName: FeatureName, state: FeatureState)
  fun revertToDefault(featureName: FeatureName)
  fun setNewDefault(featureName: FeatureName, defaultState: FeatureState)
  fun initialize()
}
