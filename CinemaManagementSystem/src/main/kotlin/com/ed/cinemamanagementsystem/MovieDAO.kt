package com.ed.cinemamanagementsystem

// A interface de Data Acess Object serve para separar a logica de acesso aos dados da lógica de negócios da aplicação

interface MovieDAO {
    fun addMovieStart(movie: Movie)
    fun addMovieCustomP(movie: Movie, position: Int)
    fun addMovieEnd(movie: Movie)
    fun removeMovie(id: Int): Movie?
    fun updateMovie(id: Int, novoFilme: Movie)
    fun listMovies(): List<Movie>
    fun searchMovieByID(id: Int): Movie?
    fun deleteMovieByPosition(position: Int): Movie?
    fun isEmpty(): Boolean
    fun qtdMovies(): Int
    fun getAllMovies(): List<Movie>
}
