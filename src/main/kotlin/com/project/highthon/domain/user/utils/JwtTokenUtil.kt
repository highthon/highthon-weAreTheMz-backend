package com.project.highthon.domain.user.utils

import com.project.highthon.domain.user.presentation.data.response.TokenResponse

interface JwtTokenUtil {

    fun generateJwtToken(email: String): TokenResponse

}