package com.example.week3.dto.request

data class OrderRequestDto(
    var memberId: Long,
    var menuList: List<Long>
) {

}
