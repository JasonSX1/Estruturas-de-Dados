package com.ed.cinemamanagementsystem

// A interface de Data Acess Object serve para separar a logica de acesso aos dados da lógica de negócios da aplicação

interface SessionDAO {
    fun updateSession(sessao: Session)
    fun removeSession(id: Int)
    fun updateSession(id: Int, novaSessao: Session)
    fun listSessions(): List<Session>
    fun searchSessionByID(id: Int): Session?
}
