package com.project.highthon.domain.user.service

import com.project.highthon.domain.user.presentation.data.request.SignInRequest
import com.project.highthon.domain.user.presentation.data.request.SignUpRequest
import com.project.highthon.domain.user.presentation.data.response.TokenResponse

interface UserAuthService {

    fun signUp(request: SignUpRequest)

    fun signIn(request: SignInRequest): TokenResponse

}