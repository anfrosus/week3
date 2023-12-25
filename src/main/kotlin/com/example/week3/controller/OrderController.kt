package com.example.week3.controller

import com.example.week3.dto.OrderRequestDto
import com.example.week3.dto.OrderResponseDto
import com.example.week3.service.OrderService
import com.example.week3.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class OrderController(
    private val orderService: OrderService,
    private val paymentService: PaymentService
) {

    @PostMapping("/orders")
    fun createOrder(@RequestBody orderRequest: OrderRequestDto) : ResponseEntity<String>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(paymentService.doPayment(orderRequest).toString())
    }
}