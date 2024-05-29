package app.core.entity.user.service

import app.core.entity.subscription.SubscriptionCode
import app.core.entity.subscription.SubscriptionRepo
import app.core.entity.user.dto.User
import app.core.entity.user.dto.UserFullName
import app.core.entity.user.repo.UserModel
import app.core.entity.user.repo.UserRepo
import app.core.entity.user.repo.UserStatus
import app.core.utils.date.getCurrentAlmatyLocalDateTime
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
  private val repo: UserRepo,
  private val subscriptionRepo: SubscriptionRepo
) {

  fun createUser(
    username: String,
    phone: String,
    email: String,
    fullName: UserFullName,
    subscriptionCode: SubscriptionCode,
    password: String
  ) = repo.save(
    UserModel(
      email = email,
      username = username,
      password = password,
      fullName = fullName,
      phoneNumber = phone,
      createdAt = getCurrentAlmatyLocalDateTime(),
      updatedAt = getCurrentAlmatyLocalDateTime(),
      subscription = subscriptionRepo.findByCode(subscriptionCode)!!,
      status = UserStatus.VERIFICATION_PENDING
    )
  ).toDTO()

  fun getAllUsers() =
    repo.findAll().map { it.toDTO() }

  fun getByUsername(username: String) =
    repo.findByUsername(username)?.toDTO()

  fun getByPhone(phone: String) =
    repo.findByPhoneNumber(phone)?.toDTO()

  fun getByEmail(email: String) =
    repo.findByEmail(email)?.toDTO()

  fun getById(id: Long) =
    repo.findByIdOrNull(id)?.toDTO()

  fun getExtendedByUsername(username: String) =
    repo.findByUsername(username)?.toExtendedDTO()

  fun getExtendedByEmail(email: String) =
    repo.findByEmail(email)?.toExtendedDTO()

  fun updateUser(
    id: Long,
    username: String? = null,
    password: String? = null,
    fullName: UserFullName? = null,
    phoneNumber: String? = null,
    email: String? = null,
    status: UserStatus? = null
  ): User {
    val model = repo.findById(id).orElseThrow()
    username?.let { model.username = username }
    password?.let { model.password = password }
    fullName?.let { model.fullName = fullName }
    phoneNumber?.let { model.phoneNumber = phoneNumber }
    email?.let { model.email = email }
    status?.let { model.status = status }

    return model.toDTO()
  }
}
