package com.example.week3.controller

import com.example.week3.dto.request.MenuRequestDto
import com.example.week3.dto.response.MenuResponseDto
import com.example.week3.model.Menu
import com.example.week3.service.MenuService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping("menus/week")
    fun getMenuOfWeekList() : ResponseEntity<List<MenuResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.getMenuOfWeek())
    }

    @GetMapping("/menus")
    fun getMenuList(): ResponseEntity<List<MenuResponseDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                menuService.getMenuList()
            )
    }

    //메뉴 추가
    @PostMapping("/menus")
    fun addMenu(
        @RequestBody menuRequest: MenuRequestDto
    ): ResponseEntity<MenuResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(menuService.createMenu(menuRequest))
    }
    //메뉴 삭제

    //메뉴 수정

    //pageableTest
    @GetMapping("/test")
    fun pageable(
        @PageableDefault(size = 1, sort = ["id"]) pageable: Pageable
    ): ResponseEntity<Page<MenuResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.test(pageable))

    }

}