package com.example.week3.service

import com.example.week3.dto.OrderRequestDto
import com.example.week3.dto.OrderResponseDto
import com.example.week3.enums.OrderStatusEnum
import com.example.week3.model.Order
import com.example.week3.model.toResponse
import com.example.week3.repository.MemberRepository
import com.example.week3.repository.MenuRepository
import com.example.week3.repository.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val menuRepository: MenuRepository,
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository
) {

    @Transactional
    fun createOrder(orderRequest: OrderRequestDto): OrderResponseDto {

        val member = memberRepository.findByIdOrNull(orderRequest.memberId)
            ?: throw Exception()
        val menuList = menuRepository.findMenuListById(orderRequest.menuList).toMutableList()

        val totalAmount = menuList.sumOf { it.price }

        if (member.balance < totalAmount) {
            throw Exception()
        }
        member.balance -= totalAmount

        val newOrder = Order(
            member = member,
            menuList = menuList,
            totalAmount = totalAmount,
            status = OrderStatusEnum.WAITING
        )

        return orderRepository.save(newOrder).toResponse()
    }

    @Transactional
    fun completeOrder(orderId: Long){
        val order = orderRepository.findByIdOrNull(orderId)
            ?: throw Exception()
        order.menuList.map { it.sales += 1 }
        order.status = OrderStatusEnum.COMPLETED
    }

    @Transactional
    fun cancelOrder(orderResponse: OrderResponseDto) {
        val order = orderRepository.findByIdOrNull(orderResponse.orderId)
            ?: throw Exception()
        val member = memberRepository.findByIdOrNull(orderResponse.memberId)
            ?: throw Exception()
        member.balance += orderResponse.totalAmount
        order.status = OrderStatusEnum.CANCELED
    }


}