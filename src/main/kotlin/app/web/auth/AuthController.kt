package app.web.auth

import app.useCase.user.LoginUser
import app.useCase.user.LoginViaEmail
import app.useCase.user.RegisterUser
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
  private val loginViaEmail: LoginViaEmail,
  private val registerUser: RegisterUser,
  private val loginUser: LoginUser
) {

  @PostMapping("/register")
  @ResponseBody
  fun registerUser(@RequestBody body: RegisterUser.Input) =
    registerUser.handle(body)

  @PostMapping("/login")
  @ResponseBody
  fun loginUser(@RequestBody body: LoginUser.Input) =
    loginUser.handle(body)

  @PostMapping("/login-via-email")
  @ResponseBody
  fun loginViaEmail(@RequestParam uuid: String) =
    loginViaEmail.handle(LoginViaEmail.Input(uuid))
}
