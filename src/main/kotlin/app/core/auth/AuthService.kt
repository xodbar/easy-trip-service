package app.core.auth

import app.core.cache.CacheService
import app.core.entity.user.dto.User
import app.core.entity.user.repo.UserStatus
import app.core.entity.user.service.UserService
import org.springframework.stereotype.Service
import java.io.Serializable
import java.time.Duration
import java.time.Instant
import java.util.*

@Service
class AuthService(
  private val userService: UserService,
  private val cacheService: CacheService
) {

  fun createSession(userId: Long): String {
    val user = userService.getById(userId)

    if (user?.status != UserStatus.ACTIVE && user?.status != UserStatus.VERIFICATION_PENDING)
      throw RuntimeException("User status is invalid [userId=$userId]")

    val sessionId = UUID.randomUUID().toString()
    cacheService.putObject(
      key = sessionId,
      obj = SessionInfo(user.id, Instant.now()),
      expirationMillis = Duration.ofHours(1).toMillis()
    )

    return sessionId
  }

  fun getUserFromSession(sessionId: String): User? {
    val sessionInfo = cacheService.getObject(sessionId, SessionInfo::class.java) ?: return null
    val user = userService.getById(sessionInfo.userId)
      ?: throw RuntimeException("Invalid userId for session [sessionId=$sessionId]")

    if (user.status != UserStatus.ACTIVE)
      throw RuntimeException("User status is invalid [userId=${user.id}]")

    return user
  }

  data class SessionInfo(
    val userId: Long,
    val issuedAt: Instant
  ) : Serializable
}
