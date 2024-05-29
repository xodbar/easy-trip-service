package app.useCase.user.register

enum class UserRegistrationResult {
  ALREADY_REGISTERED_EMAIL,
  ALREADY_REGISTERED_USERNAME,
  ALREADY_REGISTERED_PHONE,
  SUCCESS
}
