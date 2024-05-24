package com.ed.cinemamanagementsystem

class ListaDinamicaFilmes: FilmeDAO {

    private var ponteiroInicio: NoDuplo? = null
    private var ponteiroFim: NoDuplo? = null
    private var quantidade = 0

    override fun adicionarFilmeInicio(filmes: Filme) {
        TODO("Not yet implemented")
    }

    override fun adicionarFilmePerson(filmes: Filme) {
        TODO("Not yet implemented")
    }

    override fun adicionarFilmeFinal(filmes: Filme) {
        TODO("Not yet implemented")
    }

    override fun removerFilme(id: Int): Filme{
        TODO("Not yet implemented")
    }

    override fun atualizarFilme(id: Int, novoFilme: Filme){
        TODO("Not yet implemented")
    }

    override fun listarFilmes(): List<Filme>{
        TODO("Not yet implemented")
    }

    override fun buscarFilmePorId(id: Int): Filme?{
        TODO("Not yet implemented")
    }

    override fun estaVazia(): Boolean {
        TODO("Not yet implemented")
    }

    override fun quantidadeFilmes(): Int {
        TODO("Not yet implemented")
    }

}