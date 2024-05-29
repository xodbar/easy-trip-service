package app.web.auth

import app.useCase.user.forgetPassword.ForgetPassword
import app.useCase.user.forgetPassword.ForgetPasswordInput
import app.useCase.user.login.LoginUser
import app.useCase.user.login.LoginUserInput
import app.useCase.user.loginViaEmail.LoginUserViaEmailInput
import app.useCase.user.loginViaEmail.LoginViaEmail
import app.useCase.user.register.RegisterUser
import app.useCase.user.register.RegisterUserInput
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
@RequestMapping("/auth")
@CrossOrigin
class AuthController(
  private val loginViaEmail: LoginViaEmail,
  private val registerUser: RegisterUser,
  private val loginUser: LoginUser,
  private val forgetPassword: ForgetPassword,
  private val env: Environment
) {

  @PostMapping("/register")
  @ResponseBody
  fun registerUser(@RequestBody body: RegisterUserInput) =
    registerUser.handle(body)

  @PostMapping("/login")
  @ResponseBody
  fun loginUser(@RequestBody body: LoginUserInput) =
    loginUser.handle(body)

  @GetMapping("/login-via-email")
  @ResponseBody
  fun loginViaEmail(@RequestParam uuid: String, response: HttpServletResponse): ResponseEntity<Any> {
    val sessionId = loginViaEmail.handle(LoginUserViaEmailInput(uuid)).sessionId
    response.addCookie(Cookie("sessionId", sessionId).apply {
      path = "/"
      maxAge = 60 * 60 * 24 // Для примера, живет 1 день
    })

    val headers = HttpHeaders()
    headers.add("Location", "http://${InetAddress.getLocalHost().hostAddress}:${env.getProperty("server.port")}/html/ui-home.html")

    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build()
  }

  @PostMapping("/forget-password")
  @ResponseBody
  fun loginViaEmail(@RequestBody body: ForgetPasswordInput) =
    forgetPassword.handle(body)
}
