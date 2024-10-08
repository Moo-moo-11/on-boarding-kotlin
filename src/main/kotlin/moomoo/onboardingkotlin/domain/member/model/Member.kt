package moomoo.onboardingkotlin.domain.member.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(

    val username: String,

    val password: String,

    val nickname: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole = UserRole.USER,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)