package com.project.highthon.domain.user.presentation.data.request

import com.project.highthon.domain.user.domain.User

data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String,
)

fun SignUpRequest.toEntity(password: String): User =
    User(
        id = -1,
        email = email,
        password = password,
        name = name
    )
