package com.ed.cinemamanagementsystem

import java.time.LocalDateTime

data class Ticket(
    val ticketId: Int,
    val sessionId: Int,
    val movieName: String,
    val customerId: String,
    val purchaseTime: LocalDateTime,
    val seatRow: Int,
    val seatCol: Int,
    val price: Double
)