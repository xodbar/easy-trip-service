package app.core.feature.service

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
internal class FeatureFlagInitializer(
  private val featureFlagService: FeatureFlagService
) {
  @EventListener(ContextRefreshedEvent::class)
  fun initialize(event: ContextRefreshedEvent) {
    featureFlagService.initialize()
  }
}
