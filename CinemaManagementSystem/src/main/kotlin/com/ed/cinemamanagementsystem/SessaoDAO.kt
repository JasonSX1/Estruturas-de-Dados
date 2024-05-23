package com.ed.cinemamanagementsystem

interface SessaoDAO {
    fun adicionarSessao(sessao: Sessao)
    fun removerSessao(id: Int)
    fun atualizarSessao(id: Int, novaSessao: Sessao)
    fun listarSessoes(): List<Sessao>
    fun buscarSessaoPorId(id: Int): Sessao?
}
