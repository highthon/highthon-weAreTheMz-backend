package com.project.highthon.domain.reels.presentation

import com.project.highthon.domain.reels.presentation.data.request.CreateReelsRequest
import com.project.highthon.domain.reels.presentation.data.response.ReelsApplicantListResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsListResponse
import com.project.highthon.domain.reels.presentation.data.response.ReelsResponse
import com.project.highthon.domain.reels.service.ReelsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("reels")
class ReelsController(
    private val reelsService: ReelsService
) {

    @PostMapping
    fun createReels(
        @RequestPart(value = "requestDto") request: CreateReelsRequest,
        @RequestPart file: MultipartFile
    ): ResponseEntity<Void> =
        reelsService.createReels(request, file)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("list")
    fun findReelsByHighView(): ResponseEntity<ReelsListResponse> =
        reelsService.findReelsByHighView()
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun findReelsByCategory(@RequestParam category: String): ResponseEntity<ReelsListResponse> =
        reelsService.findReelsByCategory(category)
            .let { ResponseEntity.ok(it) }

    @GetMapping("{id}")
    fun findReelsById(@PathVariable id: Long): ResponseEntity<ReelsResponse> =
        reelsService.findReelsById(id)
            .let { ResponseEntity.ok(it) }

    @GetMapping("applicant")
    fun findReelsApplicantByHashTag(@RequestParam hashtag: String): ResponseEntity<ReelsApplicantListResponse> =
        reelsService.findReelsApplicantByHashTag(hashtag)
            .let { ResponseEntity.ok(it) }

}