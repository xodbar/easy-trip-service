package app.useCase.user.register

import app.core.entity.user.dto.UserFullName
import app.core.entity.user.repo.UserStatus
import java.io.Serializable

data class RegisterUserInput(
    val username: String,
    val phone: String,
    val email: String,
    val fullName: UserFullName,
    val password: String,
    val status: UserStatus?
) : Serializable
