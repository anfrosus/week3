package com.example.week3.service

import com.example.week3.dto.request.OrderRequestDto
import com.example.week3.dto.response.OrderResponseDto
import com.example.week3.enums.OrderStatusEnum
import com.example.week3.exception.CustomException
import com.example.week3.exception.ErrorCode
import com.example.week3.model.Order
import com.example.week3.model.OrderMenu
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
            ?: throw CustomException("member", ErrorCode.NOT_FOUND)

        val menuList = menuRepository.findMenuListById(orderRequest.menuList)
        if(menuList.size != orderRequest.menuList.size){
            throw CustomException("menu", ErrorCode.NOT_FOUND)
        }

        var totalAmount: Long = menuList.sumOf { it.basePrice }

        if (member.balance < totalAmount) {
            throw CustomException("member", ErrorCode.BELOW_ZERO_AMOUNT)
        }

        member.balance -= totalAmount

        val newOrder = Order(
            member = member,
            totalAmount = totalAmount,
            status = OrderStatusEnum.COMPLETED
        )

        menuList.map {
            it.sales += 1
            newOrder.orderMenus.add(
                OrderMenu(
                    order = newOrder,
                    menu = it,
                    number = 1
                )
            )
        }

        return orderRepository.save(newOrder).toResponse()
    }

    @Transactional(readOnly = true)
    fun getOrderList() : List<OrderResponseDto> {
        val orderList = orderRepository.findAll()
        return orderList.map { it.toResponse() }
    }

//    @Transactional
//    fun completeOrder(orderId: Long){
//        val order = orderRepository.findByIdOrNull(orderId)
//            ?: throw CustomException("order", ErrorCode.NOT_FOUND)
//        order.orderMenus.map { it.menu.sales += 1 }
//        order.status = OrderStatusEnum.COMPLETED
//    }
//
//    @Transactional
//    fun cancelOrder(orderResponse: OrderResponseDto) {
//        val order = orderRepository.findByIdOrNull(orderResponse.orderId)
//            ?: throw CustomException("order", ErrorCode.NOT_FOUND)
//        val member = memberRepository.findByIdOrNull(orderResponse.memberId)
//            ?: throw CustomException("member", ErrorCode.NOT_FOUND)
//        member.balance += orderResponse.totalAmount
//        order.status = OrderStatusEnum.CANCELED
//    }

}