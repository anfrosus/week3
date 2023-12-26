package com.example.week3.model

import com.example.week3.dto.response.MenuResponseDto
import jakarta.persistence.*

@Entity
@Table(name = "MENU")
class Menu(

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "DESCRIPTION", nullable = false)
    var description: String,

    @Column(name = "PRICE", nullable = false)
    var basePrice: Long,

    @Column(name = "SALES", nullable = false)
    var sales: Long = 0,

    @OneToMany(mappedBy = "menu")
    var option: MutableList<Option> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Menu.toResponse() : MenuResponseDto {
    return MenuResponseDto(
        id!!,
        name,
        description,
        basePrice,
        sales
    )
}