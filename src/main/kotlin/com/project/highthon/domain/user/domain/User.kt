package com.project.highthon.domain.user.domain

import javax.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
)