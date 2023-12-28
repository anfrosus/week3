package com.example.week3.model

import jakarta.persistence.*
import java.sql.Time

@Entity
@Table(name = "ORDER_MENU")
class OrderMenu(
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ORDER_ID")
    var order: Order,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "MENU_ID")
    var menu: Menu,

    @Column(name = "NUMBER")
    var number: Long
) : TimeStamp() {

    init {
        order.orderMenus.add(this)
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}