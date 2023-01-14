package com.project.highthon.domain.user.presentation

import com.project.highthon.domain.user.presentation.data.response.UserInfoResponse
import com.project.highthon.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun findMyInfo(): ResponseEntity<UserInfoResponse> =
        userService.findMyInfo()
            .let { ResponseEntity.ok(it) }

}