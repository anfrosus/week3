package com.example.week3.service

import com.example.week3.dto.request.MenuRequestDto
import com.example.week3.dto.response.MenuResponseDto
import com.example.week3.model.Menu
import com.example.week3.model.toResponse
import com.example.week3.repository.MenuRepository
import com.example.week3.repository.OrderMenuRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val orderMenuRepository: OrderMenuRepository
) {
    @Transactional(readOnly = true)
    fun getMenuOfWeek(): List<MenuResponseDto> {
        return orderMenuRepository.searchMenuListAWeek().map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    fun getMenuList(): List<MenuResponseDto>? {
        return menuRepository.findAll().map { it.toResponse() }
    }

    @Transactional
    fun createMenu(menuRequest: MenuRequestDto): MenuResponseDto? {
        val menu = Menu(
            name = menuRequest.name,
            description = menuRequest.description,
            basePrice = menuRequest.price
        )
        return menuRepository.save(menu).toResponse()
    }

    @Transactional
    fun test(pageable: Pageable): Page<MenuResponseDto> {
        return menuRepository.pageableTest(pageable).map { it.toResponse() }
    }

}
