package com.project.highthon.domain.user.service.impl

import com.project.highthon.domain.user.presentation.data.response.UserInfoResponse
import com.project.highthon.domain.user.service.UserService
import com.project.highthon.domain.user.utils.UserUtil
import com.project.highthon.global.annotation.TransactionWithReadOnly
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userUtil: UserUtil
): UserService {

    @TransactionWithReadOnly
    override fun findMyInfo(): UserInfoResponse =
        userUtil.currentUser()
            .let { UserInfoResponse(it.id, it.name) }

}