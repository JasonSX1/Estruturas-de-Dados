package com.ed.cinemamanagementsystem

class DynamicMoviesList : MovieDAO {

    private var ponteiroInicio: DoubleNode<Movie>? = null
    private var ponteiroFim: DoubleNode<Movie>? = null
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

    override fun addMovieEnd(movie: Movie) {
        val tempNode = DoubleNode(movie)
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

    override fun addMovieCustomP(movie: Movie, position: Int) {
        val adjustedPosition = position - 1

        if (adjustedPosition < 0 || adjustedPosition > quantidade) {
            println("Posição inválida!")
            return
        }

        val tempNode = DoubleNode(movie)
        if (adjustedPosition == 0) {
            addMovieStart(movie)
        } else if (adjustedPosition == quantidade) {
            addMovieEnd(movie)
        } else {
            var current = ponteiroInicio
            for (i in 0 until adjustedPosition) {
                current = current?.next
            }
            tempNode.previous = current?.previous
            tempNode.next = current
            current?.previous?.next = tempNode
            current?.previous = tempNode
            quantidade++
        }
    }

    override fun removeMovie(id: Int): Movie? {
        var current = ponteiroInicio
        while (current != null) {
            if (current.data.id == id) {
                val movie = current.data
                if (current.previous != null) {
                    current.previous?.next = current.next
                } else {
                    ponteiroInicio = current.next
                }
                if (current.next != null) {
                    current.next?.previous = current.previous
                } else {
                    ponteiroFim = current.previous
                }
                quantidade--
                return movie
            }
            current = current.next
        }
        return null
    }

    override fun updateMovie(id: Int, novoFilme: Movie) {
        var current = ponteiroInicio
        while (current != null) {
            if (current.data.id == id) {
                current.data = novoFilme
                return
            }
            current = current.next
        }
        println("Filme não encontrado!")
    }

    override fun listMovies(): List<Movie> {
        val listaFilmes = mutableListOf<Movie>()
        var current = ponteiroInicio
        while (current != null) {
            listaFilmes.add(current.data)
            current = current.next
        }
        return listaFilmes
    }

    override fun searchMovieByID(id: Int): Movie? {
        var current = ponteiroInicio
        while (current != null) {
            if (current.data.id == id) {
                return current.data
            }
            current = current.next
        }
        return null
    }

    override fun isEmpty(): Boolean {
        return quantidade == 0
    }

    override fun qtdMovies(): Int {
        return quantidade
    }

    override fun getAllMovies(): List<Movie> {
        val allMovies = mutableListOf<Movie>()
        var current = ponteiroInicio
        while (current != null) {
            allMovies.add(current.data)
            current = current.next
        }
        return allMovies
    }
}