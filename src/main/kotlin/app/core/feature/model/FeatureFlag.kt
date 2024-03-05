package app.core.feature.model

import app.core.feature.FeatureName
import app.core.feature.FeatureState
import jakarta.persistence.*

@Entity
@Table(name = "feature_flag")
internal class FeatureFlag(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = -1,

  @Enumerated(EnumType.STRING)
  val featureName: FeatureName,

  @Enumerated(EnumType.STRING)
  var state: FeatureState,

  @Enumerated(EnumType.STRING)
  var defaultState: FeatureState
)
