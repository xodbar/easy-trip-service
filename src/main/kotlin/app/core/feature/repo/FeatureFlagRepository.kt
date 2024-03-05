package app.core.feature.repo

import app.core.feature.FeatureName
import app.core.feature.model.FeatureFlag
import org.springframework.data.jpa.repository.JpaRepository

internal interface FeatureFlagRepository : JpaRepository<FeatureFlag, Long> {
  fun findByFeatureName(featureName: FeatureName): FeatureFlag?
}
