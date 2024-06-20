package com.ed.cinemamanagementsystem

import java.time.LocalDateTime

data class Session(
    val id: Int,
    val numberRoom: Int,
    val sessionCapacity: Int,
    val movie: Movie,
    val startTime: LocalDateTime,
    val status: String
)