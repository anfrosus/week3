package com.example.week3.exception

class CustomException(
    val field: String,
    val errorCode: ErrorCode
): RuntimeException() {
}