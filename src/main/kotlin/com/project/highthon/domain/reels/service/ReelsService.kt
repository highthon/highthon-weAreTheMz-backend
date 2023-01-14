package com.project.highthon.domain.reels.service

import com.project.highthon.domain.reels.presentation.data.request.CreateReelsRequest
import com.project.highthon.domain.reels.presentation.data.response.ReelsApplicantListResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsApplicantResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsListResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsResponse
import com.project.highthon.domain.user.presentation.data.response.UserInfoResponse
import org.springframework.web.multipart.MultipartFile

interface ReelsService {

    fun createReels(request: CreateReelsRequest, file: MultipartFile)
    fun findReelsByHighView(): ReelsListResponse
    fun findReelsById(id: Long): ReelsResponse
    fun findReelsByCategory(category: String): ReelsListResponse
    fun findReelsApplicantByHashTag(hashTag: String): ReelsApplicantListResponse

}