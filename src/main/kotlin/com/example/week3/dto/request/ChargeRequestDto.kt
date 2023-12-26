package com.example.week3.dto.request

import jakarta.validation.constraints.Min

data class ChargeRequestDto(
    @field:Min(value = 100, message = "최소 충전금액은 100원 이상")
    var chargeAmount: Long
) {
}