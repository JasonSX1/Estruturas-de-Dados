package com.ed.cinemamanagementsystem

import java.time.LocalDateTime
import java.time.LocalTime

data class Session(
    val id: Int,
    val numberRoom: String,
    val sessionCapacity: Int,
    val movie: Movie?,
    val startTime: LocalTime?,
    val status: SessionStatus
)