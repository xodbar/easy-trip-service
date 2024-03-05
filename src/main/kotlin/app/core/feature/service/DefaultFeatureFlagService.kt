package app.core.feature.service

import app.core.feature.FeatureName
import app.core.feature.FeatureState
import app.core.feature.model.FeatureFlag
import app.core.feature.repo.FeatureFlagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class DefaultFeatureFlagService internal constructor(
  private val featureFlagRepo: FeatureFlagRepository
) : FeatureFlagService {


  @Transactional(readOnly = true)
  override fun getAll(): Map<FeatureName, FeatureState> {
    return featureFlagRepo.findAll()
      .sortedBy { it.featureName.ordinal }
      .associate {
        it.featureName to it.state
      }
  }

  @Transactional(readOnly = true)
  override fun get(featureName: FeatureName): FeatureState {
    val flag = getFlag(featureName)
    return flag.state
  }

  @Transactional
  override fun set(featureName: FeatureName, state: FeatureState) {
    val featureFlag = getFlag(featureName)
    featureFlag.state = state
  }

  @Transactional
  override fun revertToDefault(featureName: FeatureName) {
    val featureFlag = getFlag(featureName)
    featureFlag.state = featureFlag.defaultState
  }

  @Transactional
  override fun setNewDefault(featureName: FeatureName, defaultState: FeatureState) {
    val featureFlag = getFlag(featureName)
    featureFlag.defaultState = defaultState
  }

  @Transactional
  override fun initialize() {
    return FeatureName.entries.forEach { featureName ->
      featureFlagRepo.findByFeatureName(featureName)
        ?: featureFlagRepo.save(
          FeatureFlag(
            featureName = featureName,
            state = featureName.defaultState,
            defaultState = featureName.defaultState
          )
        )
    }
  }

  private fun getFlag(featureName: FeatureName): FeatureFlag {
    return featureFlagRepo.findByFeatureName(featureName)
      ?: throw RuntimeException("Feature [name=$featureName] not found")
  }
}
