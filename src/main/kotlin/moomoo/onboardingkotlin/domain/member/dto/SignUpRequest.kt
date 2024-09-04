package moomoo.onboardingkotlin.domain.member.dto

data class SignUpRequest(
    val username: String,
    val password: String,
    val nickname: String
)
