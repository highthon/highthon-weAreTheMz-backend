package com.project.highthon.domain.user.utils

import com.project.highthon.domain.user.domain.User

interface UserUtil {

    fun currentUser(): User
    fun findUserById(userId: Long): User

}