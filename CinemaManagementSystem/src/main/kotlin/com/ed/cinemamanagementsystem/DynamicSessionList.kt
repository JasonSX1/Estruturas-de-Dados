package com.ed.cinemamanagementsystem

class DynamicSessionList: SessionDAO {

    private var startPointer: DoubleNode<Session>? = null
    private var endPointer: DoubleNode<Session>? = null
    private var quantity = 0

    override fun addSession(session: Session): Boolean {
        val tempNode = DoubleNode(session)
        tempNode.previous = null
        if (!isEmpty()) {
            tempNode.next = startPointer
            startPointer?.previous = tempNode
        } else {
            endPointer = tempNode
        }
        startPointer = tempNode
        quantity++
        return true
    }


    override fun removeSession(id: Int): Session? {
        var current = startPointer
        while (current != null) {
            if (current.data.id == id) {
                val session = current.data
                if (current.previous != null) {
                    current.previous?.next = current.next
                } else {
                    startPointer = current.next
                }
                if (current.next != null) {
                    current.next?.previous = current.previous
                } else {
                    endPointer = current.previous
                }
                quantity--
                return session
            }
            current = current.next
        }
        return null
    }

    override fun updateSession(id: Int, newSession: Session) {
        var current = startPointer
        while (current != null) {
            if (current.data.id == id) {
                current.data = newSession
                return
            }
            current = current.next
        }
    }

    override fun listSessions(): List<Session> {
        val sessionsList = mutableListOf<Session>()
        var current = startPointer
        while (current != null) {
            sessionsList.add(current.data)
            current = current.next
        }
        return sessionsList
    }

    override fun searchSessionByID(id: Int): Session? {
        var current = startPointer
        while (current != null) {
            if (current.data.id == id) {
                return current.data
            }
            current = current.next
        }
        return null
    }

    override fun getAllSessions(): List<Session>{
        val allSessions = mutableListOf<Session>()
        var current = startPointer
        while (current != null) {
            allSessions.add(current.data)
            current = current.next
        }
        return allSessions
    }

    override fun isEmpty(): Boolean{
        return quantity == 0
    }

}