package com.example.week3.dto.response

data class MenuResponseDto(
    var menuId: Long,
    var name: String,
    var description: String,
    var price: Long,
    var sales: Long
)
