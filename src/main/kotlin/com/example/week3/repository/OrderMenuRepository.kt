package com.example.week3.repository

import com.example.week3.model.OrderMenu
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMenuRepository: JpaRepository<OrderMenu, Long>, CustomOrderMenuRepository {
}