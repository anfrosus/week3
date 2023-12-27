package com.example.week3.repository

import com.example.week3.model.Menu
import com.example.week3.model.QMenu
import com.example.week3.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class MenuRepositoryImpl : CustomMenuRepository, QueryDslSupport() {

    val menu: QMenu = QMenu.menu

    override fun pageableTest(pageable: Pageable): Page<Menu> {
        val orderByClause = BooleanBuilder()

        val totalCount = queryFactory.select(menu.count()).from(menu).fetchOne() ?: 0L
        val query =
            queryFactory.selectFrom(menu)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifier(pageable, menu))

        println(pageable.offset)
        println(pageable.pageSize)

        println("아래꺼"+pageable.sort.first().property)
        println(pageable.sort.toList().map {
            println(it.property)
            println(it.isAscending)
            println(it.isDescending)
        })

//        if (pageable.sort.isSorted){
//            pageable.sort.toList().map {
//                when(it.property) {
//                    "id" -> query.orderBy(menu.id.desc())
//                    "price" -> query.orderBy(menu.basePrice.desc())
//                    else -> query.orderBy(menu.id.asc())
//                }
//            }
//        }else {
//            query.orderBy(menu.id.asc())
//        }

        val contents = query.fetch()



        return PageImpl(contents, pageable, totalCount)
    }

    //localhost:8080/api/test?page=1&size=2&sort=id,desc&sort=basePrice,desc
    private fun getOrderSpecifier(pageable: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>>{
        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageable.sort.toList().map {
            order -> OrderSpecifier(
                if (order.isAscending) Order.ASC else Order.DESC,
                pathBuilder.get(order.property) as Expression<Comparable<*>>
            )
        }.toTypedArray()
    }
}