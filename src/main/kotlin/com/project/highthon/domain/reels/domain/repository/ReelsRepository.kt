package com.project.highthon.domain.reels.domain.repository

import com.project.highthon.domain.reels.domain.Reels
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReelsRepository: CrudRepository<Reels, Long> {

    fun findReelsByOrderByView(): List<Reels>
    fun findReelsById(id: Long): Reels?
    fun findReelsByCategory(category: String): List<Reels>
    fun findReelsByTitleContaining(hashTag: String): List<Reels>

}