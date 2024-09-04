package moomoo.onboardingkotlin.domain.member.dto

data class UserResponse(
    val username: String,
    val nickname: String,
    val authorities: List<AuthorityDto>
)
