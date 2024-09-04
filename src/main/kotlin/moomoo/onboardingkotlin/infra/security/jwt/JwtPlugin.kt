package moomoo.onboardingkotlin.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
class JwtPlugin(
    private val jwtProperties: JwtProperties
) {
    private val key = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(Charsets.UTF_8))

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return runCatching {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject: String, role: String): String {
        return generateToken(subject, role, Duration.ofHours(jwtProperties.accessTokenExpirationHour))
    }

    fun generateToken(subject: String, role: String, expirationHour: Duration): String {
        val claims: Claims = Jwts.claims().add(mapOf("role" to role)).build()
        val now = Instant.now()
        return Jwts.builder()
            .subject(subject)
            .claims(claims)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationHour)))
            .signWith(key)
            .compact()
    }
}