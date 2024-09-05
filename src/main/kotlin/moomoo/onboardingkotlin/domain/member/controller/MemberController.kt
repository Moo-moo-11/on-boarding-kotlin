package moomoo.onboardingkotlin.domain.member.controller

import jakarta.validation.Valid
import moomoo.onboardingkotlin.domain.member.dto.LoginRequest
import moomoo.onboardingkotlin.domain.member.dto.LoginResponse
import moomoo.onboardingkotlin.domain.member.dto.MemberResponse
import moomoo.onboardingkotlin.domain.member.dto.SignUpRequest
import moomoo.onboardingkotlin.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/sign-up")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun signIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .ok(memberService.signIn(loginRequest))
    }

    @GetMapping("/is-logged-in")
    fun isLoggedIn(): ResponseEntity<String> {
        return ResponseEntity
            .ok("성공적으로 로그인 되었습니다!")
    }
}