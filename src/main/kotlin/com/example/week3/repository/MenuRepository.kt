package com.example.week3.repository

import com.example.week3.model.Menu
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface MenuRepository : JpaRepository<Menu, Long>, CustomMenuRepository{

    @Query("select m from Menu m where m.id in :idList")
    fun findMenuListById(idList: List<Long>) :List<Menu>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from Menu m where m.id in :idList")
    fun findMenuListWithLock(idList: List<Long>) :List<Menu>
}