package app.core.entity.user.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<UserModel, Long> {
  fun findByUsername(username: String): UserModel?
  fun findByPhoneNumber(phoneNumber: String): UserModel?
  fun findByEmail(email: String): UserModel?
}
