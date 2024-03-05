package app.core.feature

import app.core.feature.service.FeatureFlagService
import org.slf4j.LoggerFactory

interface Feature<F : Feature<F>> {
  fun isMocked(): Boolean = this is MockedFeature
}

internal interface EnabledFeature<F : Feature<F>> : Feature<F>
internal interface MockedFeature<F : Feature<F>> : Feature<F>

internal abstract class ToggleableFeature<F : Feature<F>>(
  protected val enabled: EnabledFeature<F>,
  protected val mocked: MockedFeature<F>,
  private val featureFlagService: FeatureFlagService
) : Feature<F> {

  protected abstract val featureName: FeatureName

  @Suppress("UNCHECKED_CAST")
  protected val delegate: F
    get() {
      val logger = LoggerFactory.getLogger(javaClass)
      logger.debug("$featureName is $featureState.")
      return when (featureState) {
        FeatureState.ENABLED -> this.enabled
        FeatureState.MOCKED -> this.mocked
      } as F
    }

  @Suppress("UNCHECKED_CAST")
  protected fun get(feature: Feature<F>): F {
    return feature as F
  }

  override fun isMocked(): Boolean {
    return featureState == FeatureState.MOCKED
  }

  protected val featureState get() = featureFlagService.get(featureName)
}

