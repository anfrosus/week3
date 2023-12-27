package com.example.week3.dto.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

data class MemberRequestDto(
    @field:NotNull(message = "널이요")
    var memberName: String,
    @field:NotNull(message = "널이요")
    var password: String
)