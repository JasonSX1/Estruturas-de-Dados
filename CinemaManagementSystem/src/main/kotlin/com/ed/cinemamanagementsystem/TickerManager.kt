package com.ed.cinemamanagementsystem

import java.time.LocalDateTime

class TicketManager {
    private val tickets = mutableListOf<Ticket>()
    private val customerTickets = mutableMapOf<Int, MutableList<Ticket>>()

    fun addTicket(ticket: Ticket) {
        tickets.add(ticket)
        val customerList = customerTickets.getOrPut(ticket.customerId) { mutableListOf() }
        customerList.add(ticket)
    }

    fun getTicketsByCustomer(customerId: Int): List<Ticket> {
        return customerTickets[customerId] ?: emptyList()
    }

    fun getTicketsBySession(sessionId: Int): List<Ticket> {
        return tickets.filter { it.sessionId == sessionId }
    }

    fun purchaseTicket(session: Session, customerId: Int, seatRow: Int, seatCol: Int): Ticket? {
        // Verifica se o assento está disponível
        val existingTickets = getTicketsBySession(session.id)
        val isSeatTaken = existingTickets.any { it.seatRow == seatRow && it.seatCol == seatCol }

        if (isSeatTaken) {
            println("Assento já ocupado")
            return null
        }


        //No caso aqui seria return ticket
        return null
    }
}
