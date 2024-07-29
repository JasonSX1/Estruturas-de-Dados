package com.ed.cinemamanagementsystem

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Order(
    val orderId: Int,
    val tickets: List<Ticket>
) {
    val customerId: String
        get() = tickets.firstOrNull()?.customerId ?: "Desconhecido"

    val movieName: String
        get() = tickets.firstOrNull()?.movieName ?: "Desconhecido"

    val ticketSummary: String
        get() = tickets.groupBy { it.ticketType ?: "Desconhecido" }
            .map { (type, ticketList) -> "${ticketList.size}x - $type" }
            .joinToString("; ")

    val seats: String
        get() = tickets.joinToString(", ") { " ${('A' + it.seatRow)}${it.seatCol + 1}" }

    val total: Double
        get() = tickets.sumOf { it.price }

    val purchaseTime: String
        get() = tickets.firstOrNull()?.purchaseTime?.format(DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy")) ?: LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy"))
}

val orderList: MutableList<Order> = mutableListOf()
var nextOrderId = 1
