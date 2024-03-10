package app.core.email

import app.core.feature.EnabledFeature
import app.core.feature.Feature
import app.core.feature.FeatureName
import app.core.feature.MockedFeature
import app.core.feature.ToggleableFeature
import app.core.feature.service.FeatureFlagService
import org.springframework.context.annotation.Primary
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

interface GmailService : Feature<GmailService> {
  fun sendEmail(to: String, subject: String, message: String)
}


@Primary
@Component
internal class ToggleableGmailService(
  enabled: EnabledFeature<GmailService>,
  mocked: MockedFeature<GmailService>,
  featureFlagService: FeatureFlagService
) : GmailService, ToggleableFeature<GmailService>(enabled, mocked, featureFlagService) {

  override val featureName: FeatureName = FeatureName.GMAIL_SERVICE

  override fun sendEmail(to: String, subject: String, message: String) {
    return delegate.sendEmail(to, subject, message)
  }
}

@Component
class EnabledGmailService(
  private val emailSender: JavaMailSender
) : GmailService, EnabledFeature<GmailService> {
  override fun sendEmail(to: String, subject: String, message: String) {
    val simpleMailMessage = SimpleMailMessage()
    simpleMailMessage.setTo(to)
    simpleMailMessage.subject = subject
    simpleMailMessage.text = message

    emailSender.send(simpleMailMessage)
  }
}

@Component
class MockedGmailService : GmailService, MockedFeature<GmailService> {
  override fun sendEmail(to: String, subject: String, message: String) {
    // do nothing
  }
}
