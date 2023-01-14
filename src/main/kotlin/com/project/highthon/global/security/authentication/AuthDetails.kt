package com.project.highthon.global.security.authentication

import com.project.highthon.domain.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections


open class AuthDetails(
    val user: User
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = Collections.emptyList()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.email

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false

}