package com.ed.cinemamanagementsystem

interface FilmeDAO {
    fun adicionarFilmeInicio(filme: Filme)
    fun adicionarFilmePerson(filme: Filme)
    fun adicionarFilmeFinal(filme: Filme)
    fun removerFilme(id: Int): Filme?
    fun atualizarFilme(id: Int, novoFilme: Filme)
    fun listarFilmes(): List<Filme>
    fun buscarFilmePorId(id: Int): Filme?
    fun estaVazia(): Boolean
    fun quantidadeFilmes(): Int
}