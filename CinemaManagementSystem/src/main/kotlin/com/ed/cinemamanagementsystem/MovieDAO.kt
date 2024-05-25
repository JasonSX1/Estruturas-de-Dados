package com.ed.cinemamanagementsystem

interface MovieDAO {
    fun addMovieStart(filme: Movie)
    fun addMovieCustomP(filme: Movie)
    fun addMovieEnd(filme: Movie)
    fun removeMovie(id: Int): Movie?
    fun updateMovie(id: Int, novoFilme: Movie)
    fun listMovies(): List<Movie>
    fun searchMovieByID(id: Int): Movie?
    fun isEmpty(): Boolean
    fun qtdMovies(): Int
}