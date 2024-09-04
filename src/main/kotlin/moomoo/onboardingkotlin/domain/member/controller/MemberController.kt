package moomoo.onboardingkotlin.domain.member.controller

import moomoo.onboardingkotlin.domain.member.dto.LoginRequest
import moomoo.onboardingkotlin.domain.member.dto.LoginResponse
import moomoo.onboardingkotlin.domain.member.dto.SignUpRequest
import moomoo.onboardingkotlin.domain.member.dto.UserResponse
import moomoo.onboardingkotlin.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun signIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .ok(memberService.signIn(loginRequest))
    }
}