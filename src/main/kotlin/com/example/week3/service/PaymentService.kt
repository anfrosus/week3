package com.example.week3.service
//
//import com.example.week3.dto.request.OrderRequestDto
//import com.example.week3.dto.response.OrderResponseDto
//import com.example.week3.repository.OrderRepository
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.stereotype.Service
//
//@Service
//class PaymentService(
//    private val orderService: OrderService,
//    private val orderRepository: OrderRepository
//) {
//    fun doPayment(orderRequest: OrderRequestDto) : ResponseEntity<String>{
//        //TODO: try - catch 로 DB EXCEPTION catch해야 할듯?
//        val orderResponse = orderService.createOrder(orderRequest)
//
//        if (payment(orderResponse) == true) {
//            orderService.completeOrder(orderResponse.orderId)
//            return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body("주문 성공")
//        } else {
//            //TODO: 만약 취소 기능이 있다면, 중간에 취소누르면 ?
//            orderService.cancelOrder(orderResponse)
//            return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("주문 실패")
//        }
//
//
//    }
//
//    //결제
//    fun payment(orderResponse: OrderResponseDto): Boolean {
//        Thread.sleep(1000)
//        return true
//    }
//
//    private fun checkBalance(orderResponse: OrderResponseDto) {
//        println(orderResponse.memberBalance)
//        println(orderResponse.totalAmount)
//
//        if (orderResponse.memberBalance < orderResponse.totalAmount) {
//            throw Exception()
//        }
//    }
//}