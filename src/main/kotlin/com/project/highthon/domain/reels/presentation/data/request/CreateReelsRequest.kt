package com.project.highthon.domain.reels.presentation.data.request

import com.project.highthon.domain.reels.domain.Reels
import com.project.highthon.domain.user.domain.User

data class CreateReelsRequest(
    val title: String,
    val category: String
)

fun CreateReelsRequest.toEntity(url: String, user: User): Reels =
    Reels(
        id = -1,
        videoUrl = "https://mzip-s3-bucket.s3.ap-northeast-2.amazonaws.com/REELS$url",
        user = user,
        title = title,
        category = category,
        view = 0
    )
