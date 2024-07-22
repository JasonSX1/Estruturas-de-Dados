package com.ed.cinemamanagementsystem

import java.time.LocalDateTime

data class Session(
    val id: Int,
    val numberRoom: String,
    val sessionCapacity: Int,
    val sessionDisponibility: Int,
    val movie: Movie?,
    val startTime: LocalDateTime?,
    val status: SessionStatus,
    val rows: Int,
    val cols: Int
)