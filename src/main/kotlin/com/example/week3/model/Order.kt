package com.example.week3.model

import com.example.week3.dto.response.OrderResponseDto
import com.example.week3.enums.OrderStatusEnum
import jakarta.persistence.*

@Entity
@Table(name = "ORDERS")
class Order(
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    var member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderMenus: MutableSet<OrderMenu> = mutableSetOf(),

    @Column(name = "TOTAL_AMOUNT")
    var totalAmount: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    var status: OrderStatusEnum
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Order.toResponse(): OrderResponseDto {
    return OrderResponseDto(
        orderId = id!!,
        totalAmount = totalAmount,
        orderStatus = status.toString(),
        memberId = member.id!!,
        menuList = orderMenus.map { it.menu.toResponse() }
    )
}