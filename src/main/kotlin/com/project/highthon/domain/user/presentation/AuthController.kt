package com.project.highthon.domain.user.presentation

import com.project.highthon.domain.user.presentation.data.request.SignInRequest
import com.project.highthon.domain.user.presentation.data.request.SignUpRequest
import com.project.highthon.domain.user.presentation.data.response.TokenResponse
import com.project.highthon.domain.user.service.UserAuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthController(
    private val userAuthService: UserAuthService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignUpRequest): ResponseEntity<Void> =
        userAuthService.signUp(request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signin(@RequestBody request: SignInRequest): ResponseEntity<TokenResponse> =
        userAuthService.signIn(request)
            .let { ResponseEntity.ok(it) }

}