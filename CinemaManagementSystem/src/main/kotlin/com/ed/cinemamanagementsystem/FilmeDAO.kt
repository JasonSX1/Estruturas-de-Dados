package com.ed.cinemamanagementsystem

interface FilmeDAO {
    fun adicionarFilme(filme: Filme)
    fun removerFilme(id: Int)
    fun atualizarFilme(id: Int, novoFilme: Filme)
    fun listarFilmes(): List<Filme>
    fun buscarFilmePorId(id: Int): Filme?
}