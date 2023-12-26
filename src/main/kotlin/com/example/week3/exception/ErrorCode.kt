package com.example.week3.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    NOT_FOUND(HttpStatus.NOT_FOUND, " 을/를 찾을 수 없습니다."),
    BELOW_ZERO_AMOUNT(HttpStatus.BAD_REQUEST, "포인트가 부족합니다.")
}