package moomoo.onboardingkotlin.domain.member.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import moomoo.onboardingkotlin.domain.member.model.Member
import org.springframework.security.crypto.password.PasswordEncoder

data class SignUpRequest(
    @field:Size(min = 5, max = 20, message = "유저이름 5자에서 20자 사이로 설정해야합니다")
    @field:Pattern(
        regexp = """[a-zA-Z\d]{5,20}""",
        message = "유저이름은 알파벳 대소문자, 숫자로 구성되어야 합니다"
    )
    val username: String,

    @field:Size(min = 8, max = 20, message = "비밀번호는 8자에서 20자 사이로 설정해야합니다")
    @field:Pattern(
        regexp = """(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}""",
        message = "비밀번호는 영어, 숫자, 특수문자를 포함해야 합니다"
    )
    val password: String,

    @field:Size(min = 2, max = 12, message = "닉네임은 2자에서 12자 사이로 설정해야합니다")
    @field:Pattern(
        regexp = """[가-힣a-zA-Z0-9]{2,12}""",
        message = "닉네임은 한글, 영문, 숫자로 구성되어야 합니다"
    )
    val nickname: String
) {
    fun toEntity(passwordEncoder: PasswordEncoder): Member {
        if (password.contains(username)) throw IllegalArgumentException("비밀번호는 유저이름을 포함할 수 없습니다")

        return Member(
            username = username,
            password = passwordEncoder.encode(password),
            nickname = nickname
        )
    }
}
