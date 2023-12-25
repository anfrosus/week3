package com.example.week3.model

import com.example.week3.dto.MenuResponseDto
import jakarta.persistence.*

@Entity
@Table(name = "MENU")
class Menu(

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "DESCRIPTION", nullable = false)
    var description: String,

    @Column(name = "PRICE", nullable = false)
    var price: Long,

    @Column(name = "SALES", nullable = false)
    var sales: Long = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Menu.toResponse() : MenuResponseDto{
    return MenuResponseDto(
        name,
        description,
        price
    )
}