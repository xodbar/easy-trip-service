package app.web.user

import app.core.entity.user.dto.BasicUserInfo
import app.core.entity.user.service.UserService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@CrossOrigin
class UserController(
  private val userService: UserService
) {

  @GetMapping("/{id}/get-basic-info")
  @ResponseBody
  fun getBasicInfo(@PathVariable id: Long): BasicUserInfo? {
    return userService.getById(id)?.toBasic()
  }
}
