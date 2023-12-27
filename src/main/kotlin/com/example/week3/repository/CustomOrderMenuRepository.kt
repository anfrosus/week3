package com.example.week3.repository

import com.example.week3.model.Menu

interface CustomOrderMenuRepository {
    fun searchMenuListAWeek() : MutableList<Menu>
}