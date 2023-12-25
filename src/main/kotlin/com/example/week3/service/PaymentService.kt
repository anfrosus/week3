package com.example.week3.service

import com.example.week3.dto.OrderRequestDto
import com.example.week3.dto.OrderResponseDto
import com.example.week3.enums.OrderStatusEnum
import com.example.week3.model.Order
import com.example.week3.repository.MemberRepository
import com.example.week3.repository.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val orderService: OrderService,
    private val orderRepository: OrderRepository
) {

    fun doPayment(orderRequest: OrderRequestDto) {
        try {
            val orderResponse = orderService.createOrder(orderRequest)
            println("오다는 만들어짐")
            if (payment(orderResponse) == true){
                orderService.completeOrder(orderResponse.orderId)
                println("컴플리트는 됨")
            }else {
                orderService.cancelOrder(orderResponse)
            }

        }catch (e: Exception){

            println("doSomething")
        }

    }

    //결제
    fun payment(orderResponse: OrderResponseDto) : Boolean{
        checkBalance(orderResponse)
        Thread.sleep(1000)
        return true
    }

    private fun checkBalance(orderResponse: OrderResponseDto) {
        if (orderResponse.memberBalance < orderResponse.totalAmount) {
            throw Exception()
        }
    }
}