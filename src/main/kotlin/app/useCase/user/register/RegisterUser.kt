package app.useCase.user.register

import app.core.cache.CacheService
import app.core.email.GmailService
import app.core.entity.subscription.SubscriptionCode
import app.core.entity.user.service.UserService
import app.useCase.UseCase
import app.useCase.user.loginViaEmail.LoginViaEmailElement
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.net.InetAddress
import java.net.URLEncoder
import java.time.Duration
import java.util.*

@Component
class RegisterUser(
  private val gmailService: GmailService,
  private val cacheService: CacheService,
  private val userService: UserService,
  private val env: Environment
) : UseCase<RegisterUserInput, UserRegistrationResult>() {

  private val logger = LoggerFactory.getLogger(this::class.java)

  @Transactional
  override fun handle(input: RegisterUserInput): UserRegistrationResult {
    userService.getByUsername(input.username)?.let { return UserRegistrationResult.ALREADY_REGISTERED_USERNAME }
    userService.getByEmail(input.email)?.let { return UserRegistrationResult.ALREADY_REGISTERED_EMAIL }
    userService.getByPhone(input.phone)?.let { return UserRegistrationResult.ALREADY_REGISTERED_PHONE }

    val hashedPassword = BCrypt.hashpw(input.password, BCrypt.gensalt())
    val user = userService.createUser(
      username = input.username,
      phone = input.phone,
      email = input.email,
      fullName = input.fullName,
      subscriptionCode = SubscriptionCode.BASE,
      password = hashedPassword
    )

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
      to = input.email,
      subject = "Email verification for Easy-Trip",
      message = "Follow this link to finish your registration on Easy-Trip service: ${addr}\nDon't tell the "
    )

    return UserRegistrationResult.SUCCESS
  }

}
