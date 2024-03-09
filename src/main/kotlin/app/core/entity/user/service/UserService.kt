package app.core.entity.user.service

import app.core.entity.subscription.SubscriptionCode
import app.core.entity.subscription.SubscriptionRepo
import app.core.entity.user.dto.UserFullName
import app.core.entity.user.repo.UserModel
import app.core.entity.user.repo.UserRepo
import app.core.entity.user.repo.UserStatus
import app.core.utils.date.getCurrentAlmatyLocalDateTime
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
      subscription = subscriptionRepo.findFirstByCode(subscriptionCode)!!,
      status = UserStatus.ACTIVE
    )
  ).toDTO()

  fun getByUsername(username: String) =
    repo.findFirstByUsername(username)?.toDTO()

  fun getExtendedByUsername(username: String) =
    repo.findFirstByUsername(username)?.toExtendedDTO()
}
