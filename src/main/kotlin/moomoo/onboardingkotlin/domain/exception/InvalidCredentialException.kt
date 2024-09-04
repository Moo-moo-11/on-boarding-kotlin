package moomoo.onboardingkotlin.domain.exception

data class InvalidCredentialException(
    val text: String
) : RuntimeException(
    "잘못된 인증 정보: $text"
)