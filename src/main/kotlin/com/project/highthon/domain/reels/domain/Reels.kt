package com.project.highthon.domain.reels.domain

import com.project.highthon.domain.user.domain.User
import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Reels(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reels_id")
    val id: Long,
    @Column(name = "video_url")
    val videoUrl: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val title: String,
    val category: String,
    @ColumnDefault(value = "0")
    val view: Long,
)