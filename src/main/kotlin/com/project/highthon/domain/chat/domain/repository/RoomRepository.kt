package com.project.highthon.domain.chat.domain.repository

import com.project.highthon.domain.chat.domain.Room
import org.springframework.data.repository.CrudRepository

interface RoomRepository: CrudRepository<Room, Long> {

    fun findRoomById(roomId: Long): Room?

}