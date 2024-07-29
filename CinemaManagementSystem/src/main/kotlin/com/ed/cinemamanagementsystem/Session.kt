package com.ed.cinemamanagementsystem

import java.time.LocalDateTime

data class Session(
    val id: Int,
    val numberRoom: String,
    val sessionCapacity: Int,
    var sessionDisponibility: Int,
    val movie: Movie?,
    val startTime: LocalDateTime?,
    var status: SessionStatus,
    val rows: Int,
    val cols: Int
)