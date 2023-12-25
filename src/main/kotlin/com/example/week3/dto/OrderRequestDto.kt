package com.example.week3.dto

data class OrderRequestDto(
    var memberId: Long,
    var menuList: List<Long>
) {

}
