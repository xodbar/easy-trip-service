package app.useCase.user.forgetPassword

import app.core.cache.CacheService
import app.core.email.GmailService
import app.core.entity.user.service.UserService
import app.useCase.UseCase
import app.useCase.user.loginViaEmail.LoginViaEmailElement
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.io.Serializable
import java.net.InetAddress
import java.net.URLEncoder
import java.time.Duration
import java.util.*

@Component
class ForgetPassword(
  private val userService: UserService,
  private val gmailService: GmailService,
  private val cacheService: CacheService,
  private val env: Environment
) : UseCase<ForgetPasswordInput, ForgetPasswordOutput>() {

  private val logger = LoggerFactory.getLogger(this::class.java)

  override fun handle(input: ForgetPasswordInput): ForgetPasswordOutput? {
    val user = userService.getExtendedByEmail(input.emailOrUsername)
      ?: userService.getExtendedByUsername(input.emailOrUsername)
      ?: throw RuntimeException("User with email/username [email/username=${input.emailOrUsername}] not found")

    val uuid = UUID.randomUUID().toString()
    cacheService.putObject(
      key = uuid,
      obj = LoginViaEmailElement(user.username),
      expirationMillis = Duration.ofHours(24).toMillis()
    )

    logger.info("Issued login-via-email code for ${user.username}: $uuid")

    val addr = "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}" +
      "/auth/login-via-email?uuid=${URLEncoder.encode(uuid, "UTF-8")}"

    gmailService.sendEmail(
      to = user.email,
      subject = "Email verification for Easy-Trip",
      message = "Follow this link to finish your registration on Easy-Trip service: ${addr}\nDon't tell the "
    )

    return ForgetPasswordOutput(
      sentTo = getMaskedEmail(user.email)
    )
  }

  private fun getMaskedEmail(email: String): String {
    val emailName = email.substringBefore("@")
    val emailHost = email.substringAfter("@")
    val maskedPart = "*".repeat(emailName.length - 2)

    return "${emailName[0]}$maskedPart${emailName[emailName.length - 1]}@$emailHost"
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgetPasswordInput(
  val emailOrUsername: String
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgetPasswordOutput(
  val sentTo: String
) : Serializable
