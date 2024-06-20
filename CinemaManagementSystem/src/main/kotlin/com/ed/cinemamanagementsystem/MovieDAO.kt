package com.ed.cinemamanagementsystem

// A interface de Data Acess Object serve para separar a logica de acesso aos dados da lógica de negócios da aplicação

interface MovieDAO {
    fun addMovieStart(movie: Movie)
    fun addMovieCustomP(movie: Movie, position: Int)
    fun addMovieEnd(movie: Movie)
    fun removeMovie(id: Int): Movie?
    fun updateMovie(id: Int, novoFilme: Movie)
    fun listMovies(): List<Movie> //Função para carregar os nomes dos filmes na ui de criação de nova sessão
    fun searchMovieByID(id: Int): Movie?
    fun isEmpty(): Boolean //Função criada para exibir o icone de modificacao
    fun qtdMovies(): Int //Função de controle para adição em posição personalizada
    fun getAllMovies(): List<Movie>  //Função para salvar em arquivo serializavel
}
