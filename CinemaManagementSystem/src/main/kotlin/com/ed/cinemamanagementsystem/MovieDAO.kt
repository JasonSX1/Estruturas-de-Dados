package com.ed.cinemamanagementsystem

interface MovieDAO {
    fun addMovieStart(movie: Movie)
    fun addMovieCustomP(movie: Movie, position: Int)
    fun addMovieEnd(movie: Movie)
    fun removeMovie(id: Int): Movie?
    fun updateMovie(id: Int, novoFilme: Movie)
    fun listMovies(): List<Movie>
    fun searchMovieByID(id: Int): Movie?
    fun isEmpty(): Boolean
    fun qtdMovies(): Int
}