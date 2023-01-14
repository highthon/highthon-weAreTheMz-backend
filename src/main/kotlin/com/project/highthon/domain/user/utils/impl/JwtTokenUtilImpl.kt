package com.project.highthon.domain.user.utils.impl

import com.project.highthon.domain.user.domain.repository.UserRepository
import com.project.highthon.domain.user.presentation.data.response.TokenResponse
import com.project.highthon.global.security.jwt.JwtTokenProvider
import com.project.highthon.domain.user.utils.JwtTokenUtil
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Component
class JwtTokenUtilImpl(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository
): JwtTokenUtil {

    @Transactional(rollbackFor = [Exception::class])
    override fun generateJwtToken(email: String): TokenResponse {
        val user = userRepository.findUserByEmail(email)
            ?: throw RuntimeException()
        val accessToken = jwtTokenProvider.generateAccessToken(email)
        val refreshToken = jwtTokenProvider.generateRefreshToken(email)
        return TokenResponse(accessToken, refreshToken, jwtTokenProvider.getExpiredAt())
    }

}