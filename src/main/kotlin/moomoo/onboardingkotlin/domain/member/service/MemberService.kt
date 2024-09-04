package moomoo.onboardingkotlin.domain.member.service

import moomoo.onboardingkotlin.domain.exception.InvalidCredentialException
import moomoo.onboardingkotlin.domain.exception.ModelNotFoundException
import moomoo.onboardingkotlin.domain.member.dto.LoginRequest
import moomoo.onboardingkotlin.domain.member.dto.LoginResponse
import moomoo.onboardingkotlin.domain.member.dto.MemberResponse
import moomoo.onboardingkotlin.domain.member.dto.SignUpRequest
import moomoo.onboardingkotlin.domain.member.repository.MemberRepository
import moomoo.onboardingkotlin.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) {

    fun signUp(signUpRequest: SignUpRequest): MemberResponse {
        return signUpRequest
            .also { if (isUsernameExists(it.username)) throw IllegalArgumentException("중복된 유저이름입니다") }
            .toEntity(passwordEncoder)
            .let { memberRepository.save(it) }
            .let { MemberResponse.from(it) }
    }

    fun signIn(loginRequest: LoginRequest): LoginResponse {
        return loginRequest
            .let { memberRepository.findByUsername(it.username) }
            ?.also {
                check(passwordEncoder.matches(loginRequest.password, it.password))
                { throw InvalidCredentialException("유저네임 또는 패스워드를 확인해주세요") }
            }
            ?.let { jwtPlugin.generateAccessToken(it.id.toString(), it.role.name) }
            ?.let { LoginResponse(it) }
            ?: throw ModelNotFoundException("Member", loginRequest.username)
    }

    private fun isUsernameExists(username: String): Boolean {
        return memberRepository.existsByUsername(username)
    }
}