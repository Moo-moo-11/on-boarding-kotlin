package moomoo.onboardingkotlin.domain.member.repository

import moomoo.onboardingkotlin.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Member?
}