package com.project.highthon.domain.reels.presentation.data.response

import com.project.highthon.domain.reels.domain.Reels

data class ReelsResponse(
    val id: Long,
    val videoUrl: String
) {
    constructor(reels: Reels): this(
        reels.id,
        reels.videoUrl
    )
}