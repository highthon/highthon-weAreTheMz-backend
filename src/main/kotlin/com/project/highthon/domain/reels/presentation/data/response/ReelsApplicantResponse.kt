package com.project.highthon.domain.reels.presentation.data.response

import com.project.highthon.domain.reels.domain.Reels

data class ReelsApplicantResponse(
    val userId: Long,
    val userName: String,
    val videoUrl: String,
    val title: String
) {
    constructor(reels: Reels): this(
        reels.user.id,
        reels.user.name,
        reels.videoUrl,
        reels.title
    )
}