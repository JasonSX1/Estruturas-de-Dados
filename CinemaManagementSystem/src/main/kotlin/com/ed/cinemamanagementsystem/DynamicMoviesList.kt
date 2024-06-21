package com.ed.cinemamanagementsystem

class DynamicMoviesList : MovieDAO {

    private var startPointer: DoubleNode<Movie>? = null
    private var endPointer: DoubleNode<Movie>? = null
    private var quantity = 0

    override fun addMovieStart(movie: Movie) {
        val tempNode = DoubleNode(movie)
        tempNode.previous = null
        if (!isEmpty()) {
            tempNode.next = startPointer
            startPointer?.previous = tempNode
        } else {
            endPointer = tempNode
        }
        startPointer = tempNode
        quantity++
    }

    override fun addMovieEnd(movie: Movie) {
        val tempNode = DoubleNode(movie)
        tempNode.next = null
        if (!isEmpty()) {
            tempNode.previous = endPointer
            endPointer?.next = tempNode
        } else {
            startPointer = tempNode
        }
        endPointer = tempNode
        quantity++
    }

    override fun addMovieCustomP(movie: Movie, position: Int) {
        val adjustedPosition = position - 1

        if (adjustedPosition < 0 || adjustedPosition > quantity) {
            println("Posição inválida!")
            return
        }

        val tempNode = DoubleNode(movie)
        if (adjustedPosition == 0) {
            addMovieStart(movie)
        } else if (adjustedPosition == quantity) {
            addMovieEnd(movie)
        } else {
            var current = startPointer
            for (i in 0 until adjustedPosition) {
                current = current?.next
            }
            tempNode.previous = current?.previous
            tempNode.next = current
            current?.previous?.next = tempNode
            current?.previous = tempNode
            quantity++
        }
    }

    override fun removeMovie(id: Int): Movie? {
        var current = startPointer
        while (current != null) {
            if (current.data.id == id) {
                val movie = current.data
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
                return movie
            }
            current = current.next
        }
        return null
    }

    override fun updateMovie(id: Int, newMovie: Movie) {
        var current = startPointer
        while (current != null) {
            if (current.data.id == id) {
                current.data = newMovie
                return
            }
            current = current.next
        }
    }

    override fun listMovies(): List<Movie> {
        val moviesList = mutableListOf<Movie>()
        var current = startPointer
        while (current != null) {
            moviesList.add(current.data)
            current = current.next
        }
        return moviesList
    }

    override fun searchMovieByID(id: Int): Movie? {
        var current = startPointer
        while (current != null) {
            if (current.data.id == id) {
                return current.data
            }
            current = current.next
        }
        return null
    }

    override fun isEmpty(): Boolean {
        return quantity == 0
    }

    override fun qtdMovies(): Int {
        return quantity
    }

    override fun getAllMovies(): List<Movie> {
        val allMovies = mutableListOf<Movie>()
        var current = startPointer
        while (current != null) {
            allMovies.add(current.data)
            current = current.next
        }
        return allMovies
    }
}