package com.project.highthon.domain.chat.domain

import com.project.highthon.domain.user.domain.User
import javax.persistence.*

@Entity
class Chat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    val id: Long,
    val message: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "room_id")
    val room: Room
)