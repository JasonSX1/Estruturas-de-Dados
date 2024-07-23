package com.ed.cinemamanagementsystem

import java.time.LocalDateTime

class TicketManager {
    private val tickets = mutableListOf<Ticket>()

    fun sellTicket(session: Session, customerId: String, seatRow: Int, seatCol: Int, price: Double, ticketType: String): Ticket? {
        if (session.sessionDisponibility <= 0 || !isSeatAvailable(session, seatRow, seatCol)) {
            return null // Capacidade esgotada ou assento não disponível
        }

        val ticketId = tickets.size + 1
        val movieName = session.movie?.title ?: "Filme desconhecido"
        val ticket = Ticket(
            ticketId,
            session.id,
            movieName,
            customerId,
            LocalDateTime.now(),
            seatRow,
            seatCol,
            price,
            ticketType
        )

        tickets.add(ticket)
        updateSessionDisponibility(session, -1)
        return ticket
    }

    private fun isSeatAvailable(session: Session, seatRow: Int, seatCol: Int): Boolean {
        return tickets.none { it.sessionId == session.id && it.seatRow == seatRow && it.seatCol == seatCol }
    }

    private fun updateSessionDisponibility(session: Session, change: Int) {
        session.sessionDisponibility += change
    }

    fun getTicketsForSession(session: Session): List<Ticket> {
        return tickets.filter { it.sessionId == session.id }
    }
}
