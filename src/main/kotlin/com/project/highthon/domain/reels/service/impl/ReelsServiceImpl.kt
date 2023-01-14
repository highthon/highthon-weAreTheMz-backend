package com.project.highthon.domain.reels.service.impl

import com.project.highthon.domain.reels.domain.repository.ReelsRepository
import com.project.highthon.domain.reels.presentation.data.request.CreateReelsRequest
import com.project.highthon.domain.reels.presentation.data.request.toEntity
import com.project.highthon.domain.reels.presentation.data.response.ReelsApplicantListResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsApplicantResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsListResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsResponse
import com.project.highthon.domain.reels.service.ReelsService
import com.project.highthon.domain.user.utils.UserUtil
import com.project.highthon.global.annotation.TransactionWithReadOnly
import com.project.highthon.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ReelsServiceImpl(
    private val reelsRepository: ReelsRepository,
    private val s3Service: S3Service,
    private val userUtil: UserUtil
): ReelsService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createReels(request: CreateReelsRequest, file: MultipartFile) {
        request.toEntity(url = s3Service.uploadFile(file, "REELS/"), user = userUtil.currentUser())
            .let { reelsRepository.save(it) }
    }

    @TransactionWithReadOnly
    override fun findReelsByHighView(): ReelsListResponse {
        val reelsList = reelsRepository.findReelsByOrderByView()
        return ReelsListResponse(
            reelsList.map { ReelsResponse(it) }
        )
    }

    @TransactionWithReadOnly
    override fun findReelsById(id: Long): ReelsResponse {
        val reels = reelsRepository.findReelsById(id) ?: throw RuntimeException()
        return ReelsResponse(reels.id, reels.videoUrl)
    }

    @TransactionWithReadOnly
    override fun findReelsByCategory(category: String): ReelsListResponse {
        val reelsList = reelsRepository.findReelsByCategory(category)
        return ReelsListResponse(
            reelsList.map { ReelsResponse(it) }
        )
    }

    @TransactionWithReadOnly
    override fun findReelsApplicantByHashTag(hashTag: String): ReelsApplicantListResponse {
        val reelsList = reelsRepository.findReelsByTitleContaining(hashTag)
        return ReelsApplicantListResponse(
            reelsList.map { ReelsApplicantResponse(it) }
        )
    }

}