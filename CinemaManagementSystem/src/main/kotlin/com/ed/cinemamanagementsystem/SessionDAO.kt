package com.ed.cinemamanagementsystem

interface SessionDAO {
    fun updateSession(sessao: Session)
    fun removeSession(id: Int)
    fun updateSession(id: Int, novaSessao: Session)
    fun listSessions(): List<Session>
    fun searchSessionByID(id: Int): Session?
}
