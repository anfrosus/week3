package com.example.week3.model

import jakarta.persistence.*

@Entity
@Table(name = "OPTION")
class Option(

    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    var menu: Menu,

    @Column(name = "NAME")
    var name: String,

    @Column(name = "PRICE")
    var additionalPrice: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}