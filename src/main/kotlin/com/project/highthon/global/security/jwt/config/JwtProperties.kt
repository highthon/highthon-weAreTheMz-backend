package com.project.highthon.global.security.jwt.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "jwt.secret")
data class JwtProperties(
    var key: String
)