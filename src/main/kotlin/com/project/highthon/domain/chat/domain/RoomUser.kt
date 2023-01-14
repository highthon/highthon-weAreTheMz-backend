package com.project.highthon.domain.chat.domain

import com.project.highthon.domain.user.domain.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class RoomUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_user_id")
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "room_id")
    val room: Room,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)