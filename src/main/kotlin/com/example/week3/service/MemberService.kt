package com.example.week3.service

import com.example.week3.dto.ChargeRequestDto
import com.example.week3.dto.ChargeResponseDto
import com.example.week3.dto.MemberRequestDto
import com.example.week3.dto.MemberResponseDto
import com.example.week3.enums.MemberRoleEnum
import com.example.week3.model.Member
import com.example.week3.model.toResponse
import com.example.week3.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun createMember(memberRequest: MemberRequestDto): MemberResponseDto {
        val newMember = Member(
            name = memberRequest.memberName,
            password = memberRequest.password,
            role = MemberRoleEnum.USER
        )
        return memberRepository.save(newMember).toResponse()
    }

    @Transactional
    fun addBalance(
        chargeRequest: ChargeRequestDto, memberId: Long
    ): ChargeResponseDto {
        println("들어오니? ${chargeRequest.chargeAmount}")
        val member = memberRepository.findByIdOrNull(memberId)
            ?: throw Exception()
        member.balance += chargeRequest.chargeAmount

        return ChargeResponseDto(
            memberId = member.id!!,
            memberName = member.name,
            chargeAmount = chargeRequest.chargeAmount,
            balance = member.balance
        )


    }



}
