package com.example.week3

import com.example.week3.enums.MemberRoleEnum
import com.example.week3.model.Member
import com.example.week3.model.Menu
import com.example.week3.repository.MemberRepository
import com.example.week3.repository.MenuRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Init(
    private val menuRepository: MenuRepository,
    private val memberRepository: MemberRepository
) {

    @PostConstruct
    fun menuInit() {
        var list = mutableListOf<Menu>()
        for (i in 1..10) {
            val menu = Menu(
                name = "메뉴이름$i",
                description = "메뉴설명$i",
                basePrice = i * 500L
            )
            list.add(menu)
        }
        menuRepository.saveAll(list)
    }

    @PostConstruct
    fun memberInit() {
        var member = Member(
            name = "회원1",
            password = "1234",
            role = MemberRoleEnum.ADMIN
        )
        memberRepository.save(member)
    }
}