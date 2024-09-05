package moomoo.onboardingkotlin.domain.member.dto

import moomoo.onboardingkotlin.domain.member.model.Member

data class MemberResponse(
    val username: String,
    val nickname: String,
    val authorities: String
) {
    companion object {
        fun from(member: Member): MemberResponse =
            MemberResponse(
                username = member.username,
                nickname = member.nickname,
                authorities = member.role.name
            )
    }
}
