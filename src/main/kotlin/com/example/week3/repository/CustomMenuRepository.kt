package com.example.week3.repository

import com.example.week3.model.Menu
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomMenuRepository {
    fun pageableTest(pageable: Pageable): Page<Menu>
}