package com.project.highthon.domain.chat.domain

import com.project.highthon.domain.user.domain.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Room(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    val id: Long,
    @OneToMany
    val roomUsers: MutableList<RoomUser>,
    @Column(name = "last_message")
    var lastMessage: String,
) {
    fun updateLastMessage(lastMessage: String) {
        this.lastMessage = lastMessage
    }

    fun addRoomUser(user: User) {
        this.roomUsers.add(RoomUser(
            id = -1,
            room = this,
            user = user
        ))
    }
}