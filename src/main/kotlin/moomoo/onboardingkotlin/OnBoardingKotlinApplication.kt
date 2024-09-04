package moomoo.onboardingkotlin

import moomoo.onboardingkotlin.infra.security.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(JwtProperties::class)
@SpringBootApplication
class OnBoardingKotlinApplication

fun main(args: Array<String>) {
    runApplication<OnBoardingKotlinApplication>(*args)
}
