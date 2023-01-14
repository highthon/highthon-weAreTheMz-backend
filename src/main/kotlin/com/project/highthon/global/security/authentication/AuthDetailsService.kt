package com.project.highthon.global.security.authentication

import com.project.highthon.domain.user.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun loadUserByUsername(username: String): UserDetails =
        AuthDetails(
            userRepository.findUserByEmail(username) ?: throw RuntimeException()
        )

}