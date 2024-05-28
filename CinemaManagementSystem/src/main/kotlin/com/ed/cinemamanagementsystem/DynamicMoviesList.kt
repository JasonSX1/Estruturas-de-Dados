package com.ed.cinemamanagementsystem

class DynamicMoviesList: MovieDAO {

    private var ponteiroInicio: DoubleNode? = null
    private var ponteiroFim: DoubleNode? = null
    private var quantidade = 0

    override fun addMovieStart(movie: Movie) {
        val tempNode = DoubleNode(movie)
        tempNode.previous = null
        if (!isEmpty()) {
            tempNode.next = ponteiroInicio
            ponteiroInicio?.previous = tempNode
        } else {
            ponteiroFim = tempNode
        }
        ponteiroInicio = tempNode
        quantidade++
    }
    override fun addMovieCustomP(filmes: Movie, position: Int) {
        if (position < 0 || position > quantidade) {
            println("Posição inválida!")
            return
        }

        val tempNode = DoubleNode(filmes)
        if (position == 0) {
            addMovieStart(filmes)
        } else if (position == quantidade) {
            addMovieEnd(filmes)
        } else {
            var current = ponteiroInicio
            for (i in 0 until position) {
                current = current?.next
            }
            tempNode.previous = current?.previous
            tempNode.next = current
            current?.previous?.next = tempNode
            current?.previous = tempNode
            quantidade++
        }
    }

    override fun addMovieEnd(filmes: Movie) {
        val tempNode = DoubleNode(filmes)
        tempNode.next = null
        if (!isEmpty()) {
            tempNode.previous = ponteiroFim
            ponteiroFim?.next = tempNode
        } else {
            ponteiroInicio = tempNode
        }
        ponteiroFim = tempNode
        quantidade++
    }

    override fun removeMovie(id: Int): Movie{
        TODO("Not yet implemented")
    }

    override fun updateMovie(id: Int, novoFilme: Movie){
        TODO("Not yet implemented")
    }

    override fun listMovies(): List<Movie>{
        TODO("Not yet implemented")
    }

    override fun searchMovieByID(id: Int): Movie?{
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun qtdMovies(): Int {
        TODO("Not yet implemented")
    }

}