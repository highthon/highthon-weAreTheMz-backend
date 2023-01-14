package com.project.highthon.domain.user.service.impl

import com.project.highthon.domain.user.domain.repository.UserRepository
import com.project.highthon.domain.user.presentation.data.request.SignInRequest
import com.project.highthon.domain.user.presentation.data.request.SignUpRequest
import com.project.highthon.domain.user.presentation.data.request.toEntity
import com.project.highthon.domain.user.presentation.data.response.TokenResponse
import com.project.highthon.domain.user.service.UserAuthService
import com.project.highthon.domain.user.utils.JwtTokenUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil
): UserAuthService {

    @Transactional(rollbackFor = [Exception::class])
    override fun signUp(request: SignUpRequest) {
        if(userRepository.existsUserByEmail(request.email)) throw RuntimeException()
        request.toEntity(passwordEncoder.encode(request.password))
            .let { userRepository.save(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun signIn(request: SignInRequest): TokenResponse {
        val user = userRepository.findUserByEmail(request.email) ?: throw RuntimeException()
        if(!passwordEncoder.matches(request.password, user.password)) throw RuntimeException()
        return jwtTokenUtil.generateJwtToken(user.email)
    }

}