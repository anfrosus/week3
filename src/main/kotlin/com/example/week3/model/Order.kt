package com.example.week3.model

import com.example.week3.dto.OrderResponseDto
import com.example.week3.enums.OrderStatusEnum
import jakarta.persistence.*

@Entity
@Table(name = "ORDERS")
class Order(
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    var member: Member,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "ORDER_LIST")
    var menuList: MutableList<Menu> = mutableListOf(),

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

fun Order.toResponse(): OrderResponseDto{
    return OrderResponseDto(
        orderId = id!!,
        totalAmount = totalAmount,
        orderStatus = status.toString(),
        memberId = member.id!!,
        memberBalance = member.balance
    )
}