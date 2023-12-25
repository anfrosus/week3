package com.example.week3.model

import com.example.week3.dto.MemberResponseDto
import com.example.week3.enums.MemberRoleEnum
import jakarta.persistence.*

@Entity
@Table(name = "MEMBER")
class Member(
    @Column(name = "NAME")
    var name: String,

    @Column(name = "PASSWORD")
    var password: String,

    @Column(name = "BALANCE")
    var balance: Long = 0,

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    var role: MemberRoleEnum
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Member.toResponse() : MemberResponseDto{
    return MemberResponseDto(
        memberName = name,
        memberRole = role.name
    )
}