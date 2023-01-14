package com.project.highthon.domain.chat.domain.repository

import com.project.highthon.domain.chat.domain.RoomUser
import com.project.highthon.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface RoomUserRepository: CrudRepository<RoomUser, Long> {

    fun findRoomUserByUser(user: User): List<RoomUser>

}