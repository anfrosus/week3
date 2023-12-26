package com.example.week3.dto.response

data class ChargeResponseDto(
    var memberId: Long,
    var memberName: String,
    var chargeAmount: Long,
    var balance: Long
) {
}