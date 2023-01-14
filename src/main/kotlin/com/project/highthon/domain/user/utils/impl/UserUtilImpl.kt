package com.project.highthon.domain.user.utils.impl

import com.fasterxml.jackson.databind.RuntimeJsonMappingException
import com.project.highthon.domain.user.domain.User
import com.project.highthon.domain.user.domain.repository.UserRepository
import com.project.highthon.domain.user.utils.UserUtil
import com.project.highthon.global.annotation.TransactionWithReadOnly
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserUtilImpl(
    private val userRepository: UserRepository
): UserUtil {

    @TransactionWithReadOnly
    override fun currentUser(): User {
        val email = SecurityContextHolder.getContext().authentication.name
        return userRepository.findUserByEmail(email)
            ?: throw RuntimeException()
    }

    @TransactionWithReadOnly
    override fun findUserById(userId: Long): User =
        userRepository.findUserById(userId)
            ?: throw RuntimeException()

}