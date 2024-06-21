package com.ed.cinemamanagementsystem

// A interface de Data Acess Object serve para separar a logica de acesso aos dados da lógica de negócios da aplicação

interface SessionDAO {
    fun addSession(session: Session)
    fun removeSession(id: Int)
    fun updateSession(sessao: Session)
    fun listSessions(): List<Session>
    fun searchSessionByID(id: Int): Session?
    fun getAllSessions(): List<Session> //Função para salvar a lista de sessões posteriormente
    fun isEmpty(): Boolean
}
