package moomoo.onboardingkotlin.domain.member.service

import moomoo.onboardingkotlin.domain.member.dto.LoginRequest
import moomoo.onboardingkotlin.domain.member.dto.LoginResponse
import moomoo.onboardingkotlin.domain.member.dto.SignUpRequest
import moomoo.onboardingkotlin.domain.member.dto.UserResponse
import org.springframework.stereotype.Service

@Service
class MemberService {

    fun signUp(signUpRequest: SignUpRequest): UserResponse {
        TODO()
    }

    fun signIn(loginRequest: LoginRequest): LoginResponse {
        TODO()
    }
}