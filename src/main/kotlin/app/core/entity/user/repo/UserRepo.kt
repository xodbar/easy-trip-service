package app.core.entity.user.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<UserModel, Long> {
  fun findFirstByUsername(username: String): UserModel?
  fun findFirstByPhoneNumber(phoneNumber: String): UserModel?
  fun findFirstByEmail(email: String): UserModel?
}
