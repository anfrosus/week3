package com.example.week3.controller

import com.example.week3.dto.request.OrderRequestDto
import com.example.week3.dto.response.OrderResponseDto
import com.example.week3.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class OrderController(
    private val orderService: OrderService,
) {

    @PostMapping("/orders")
    fun createOrder(@RequestBody orderRequest: OrderRequestDto): ResponseEntity<OrderResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orderService.createOrder(orderRequest))
    }

    @GetMapping("/orders")
    fun getOrderList() : ResponseEntity<List<OrderResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderList())
    }
}