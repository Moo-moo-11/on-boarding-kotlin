package moomoo.onboardingkotlin.domain.admin.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
class AdminController {

    @GetMapping("/is-admin")
    fun isAdmin(): ResponseEntity<String> {
        return ResponseEntity
            .ok("당신은 관리자입니다!")
    }
}