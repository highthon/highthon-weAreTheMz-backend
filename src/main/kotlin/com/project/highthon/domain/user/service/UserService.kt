package com.project.highthon.domain.user.service

import com.project.highthon.domain.user.presentation.data.response.UserInfoResponse

interface UserService {

    fun findMyInfo(): UserInfoResponse

}