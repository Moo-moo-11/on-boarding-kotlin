package moomoo.onboardingkotlin.infra.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import moomoo.onboardingkotlin.infra.security.UserPrincipal
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt is String) {
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    val userId = it.payload.subject.toLong()
                    val role = it.payload["role"] as String

                    val principal = UserPrincipal(id = userId, roles = setOf(role))

                    val authentication =
                        JwtAuthenticationToken(principal, WebAuthenticationDetailsSource().buildDetails(request))

                    SecurityContextHolder.getContext().authentication = authentication
                }
                .onFailure { exception ->
                    when (exception) {
                        is ExpiredJwtException -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었습니다")
                        }

                        else -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다")
                        }
                    }
                }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return if (headerValue.contains("Bearer ")) headerValue.removePrefix("Bearer ") else null
    }
}