package com.example.week3.controller

import com.example.week3.dto.request.ChargeRequestDto
import com.example.week3.dto.response.ChargeResponseDto
import com.example.week3.dto.request.MemberRequestDto
import com.example.week3.dto.response.MemberResponseDto
import com.example.week3.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MemberController(
    val memberService: MemberService
) {

    @PostMapping("/members")
    fun createMember(
        @RequestBody memberRequest: MemberRequestDto
    ) : ResponseEntity<MemberResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.createMember(memberRequest))
    }

    @PutMapping("/charges/{memberId}")
    fun chargePoint(
        @RequestBody @Validated chargeRequest: ChargeRequestDto,
        @PathVariable memberId: Long
    ): ResponseEntity<ChargeResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.addBalance(chargeRequest, memberId))
    }

}