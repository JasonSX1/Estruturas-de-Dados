package com.ed.cinemamanagementsystem

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File



//A classe de Serviço de persistencia serve para armazenar e recuperar os dados de uma fonte de armazenamento permanente, no nosso caso, o salvamento e carregamento das EDs em arquivos JSON

object MoviePersistenceService {
    fun saveMovies(movies: List<Movie>, username: String) {
        val jsonString = Json.encodeToString(movies)
        val file = File("movies_$username.json")
        file.writeText(jsonString)
    }

    fun loadMovies(username: String): List<Movie> {
        val file = File("movies_$username.json")
        return if (file.exists()) {
            val jsonString = file.readText()
            Json.decodeFromString(jsonString)
        } else {
            emptyList()
        }
    }
}
