package moomoo.onboardingkotlin.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.time.Instant
import java.util.Date
import kotlin.test.Test

class JwtPluginTest {

    private val jwtProperties: JwtProperties = mockk {
        every { secret } returns "fd01c9a6750d9c51af495858682be0892caebc79f7bdf7d274afdb7645d6a14a"
        every { issuer } returns "moomoo.onboarding"
        every { accessTokenExpirationHour } returns 2L
    }

    private val jwtPlugin = JwtPlugin(jwtProperties)

    @Test
    fun generateAccessToken은_유효한_JWT_토큰을_발급한다() {
        // Given
        val subject = "123"
        val role = "USER"

        // When
        val token = jwtPlugin.generateAccessToken(subject, role)

        // Then
        val parsedClaimsJws: Jws<Claims> = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(Charsets.UTF_8)))
            .build()
            .parseSignedClaims(token)

        assertThat(parsedClaimsJws.payload.subject).isEqualTo(subject)
        assertThat(parsedClaimsJws.payload["role"]).isEqualTo(role)
    }

    @Test
    fun validateToken은_유효한_JWT에_대해_성공을_반환해야_한다() {
        // Given
        val subject = "123"
        val role = "USER"

        val token = jwtPlugin.generateAccessToken(subject, role)

        // When
        val result = jwtPlugin.validateToken(token)

        // Then
        assertThat(result.isSuccess).isTrue
        assertThat(result.getOrNull()?.payload?.subject).isEqualTo(subject)
        assertThat(result.getOrNull()?.payload?.get("role")).isEqualTo(role)
    }

    @Test
    fun validateToken은_유효하지_않은_JWT에_대해_실패를_반환해야_한다() {
        // Given
        val invalidToken = "유효하지 않은 토큰"

        // When
        val result = jwtPlugin.validateToken(invalidToken)

        // Then
        assertThat(result.isFailure).isTrue
        assertThrows<JwtException> { result.getOrThrow() }
    }

    @Test
    fun validateToken은_만료된_JWT에_대해_실패를_반환해야_한다() {
        // Given
        val subject = "123"
        val role = "USER"

        val expiredToken = Jwts.builder()
            .subject(subject)
            .claim("role", role)
            .issuedAt(Date.from(Instant.now().minus(Duration.ofHours(1))))
            .expiration(Date.from(Instant.now().minus(Duration.ofMinutes(30))))
            .signWith(Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(Charsets.UTF_8)))
            .compact()

        // When
        val result = jwtPlugin.validateToken(expiredToken)

        // Then
        assertThat(result.isFailure).isTrue
        assertThrows<JwtException> { result.getOrThrow() }
    }

    @Test
    fun generateAccessToken은_지정된_만료_시간이_적용되어야야_한다() {
        // Given
        val subject = "123"
        val role = "USER"

        // When
        val token = jwtPlugin.generateAccessToken(subject, role)

        // Then
        val parsedClaimsJws: Jws<Claims> = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(Charsets.UTF_8)))
            .build()
            .parseSignedClaims(token)

        val issuedAt = parsedClaimsJws.payload.issuedAt.toInstant()
        val expiration = parsedClaimsJws.payload.expiration.toInstant()

        assertThat(Duration.between(issuedAt, expiration))
            .isCloseTo(Duration.ofHours(jwtProperties.accessTokenExpirationHour), Duration.ofMillis(1))
    }
}