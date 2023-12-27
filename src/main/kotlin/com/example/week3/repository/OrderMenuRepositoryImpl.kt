package com.example.week3.repository

import com.example.week3.model.Menu
import com.example.week3.model.QMenu
import com.example.week3.model.QOrderMenu
import com.example.week3.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OrderMenuRepositoryImpl : CustomOrderMenuRepository, QueryDslSupport() {
    private val orderMenu = QOrderMenu.orderMenu
    private val menu = QMenu.menu
    override fun searchMenuListAWeek(): MutableList<Menu> {
        return queryFactory.select(menu).distinct()
            .from(orderMenu)
            .join(orderMenu.menu)
            .where(
                orderMenu.createdDate.after(LocalDateTime.now().minusWeeks(1)),
//                menu.sales.eq(
//                    JPAExpressions.select(menu.sales.max()).from(menu)
//                )
            )
            .orderBy(menu.sales.desc())
            .limit(3)
            .fetch()
    }
}