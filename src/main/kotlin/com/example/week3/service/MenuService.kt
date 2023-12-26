package com.example.week3.service

import com.example.week3.dto.request.MenuRequestDto
import com.example.week3.dto.response.MenuResponseDto
import com.example.week3.model.Menu
import com.example.week3.model.toResponse
import com.example.week3.repository.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
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

}
