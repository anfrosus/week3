package com.example.week3.dto

data class OrderResponseDto(
    var orderId: Long,
    var orderStatus: String,
    var totalAmount: Long,
    var memberId: Long,
    var memberBalance: Long
) {


}
