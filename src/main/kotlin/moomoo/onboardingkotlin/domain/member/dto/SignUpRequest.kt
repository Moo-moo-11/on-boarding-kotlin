package moomoo.onboardingkotlin.domain.member.dto

import moomoo.onboardingkotlin.domain.member.model.Member
import org.springframework.security.crypto.password.PasswordEncoder

data class SignUpRequest(
    val username: String,
    val password: String,
    val nickname: String
) {
    fun toEntity(passwordEncoder: PasswordEncoder): Member {
        return Member(
            username = username,
            password = passwordEncoder.encode(password),
            nickname = nickname
        )
    }
}
