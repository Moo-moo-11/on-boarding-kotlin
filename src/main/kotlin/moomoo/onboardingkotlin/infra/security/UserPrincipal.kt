package moomoo.onboardingkotlin.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, roles: Set<String>) : this(
        id = id, authorities = roles.map { SimpleGrantedAuthority("ROLE_${it}") }
    )
}