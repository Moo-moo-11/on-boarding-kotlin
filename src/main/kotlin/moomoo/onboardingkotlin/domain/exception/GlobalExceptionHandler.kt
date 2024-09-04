package moomoo.onboardingkotlin.domain.exception

import moomoo.onboardingkotlin.domain.exception.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorDto(e.message, "403"))
    }
}