package src

import java.time.LocalTime

data class Sessao(private var filme: String,
             private var duracao: Int,
             private var producao: String,
             private var audio: String,
             private var is3D: Boolean,
             private var sala: Sala,
             private var horario: LocalTime,
             private var statusSala: Status
) {


    fun printSessao() {
        println("Informações da Sessão:")
        println("Filme: $filme")
        println("Duração: $duracao minutos")
        println("Horário: $horario")
        println("Produção: $producao")
        println("Áudio: $audio")
        println("3D: $is3D")
        println("src.Sala: ${sala.getNumeroSala()}")
        println("src.Status da sala: $statusSala")
    }

    fun getFilme(): String {
        return filme
    }

    fun getDuracao(): Int {
        return duracao
    }

    fun getProducao(): String {
        return producao
    }

    fun getAudio(): String {
        return audio
    }

    fun get3d(): Boolean {
        return is3D
    }

    fun getSala(): Sala {
        return sala
    }
    fun getStatusSala(): Status {
        return statusSala
    }

    fun setFilme(filme: String) {
        this.filme = filme
    }

    fun setDuracao(duracao: Int) {
        this.duracao = duracao
    }

    fun setProducao(producao: String) {
        this.producao = producao
    }

    fun setAudio(audio: String) {
        this.audio = audio
    }

    fun set3d(is3D: Boolean) {
        this.is3D = is3D
    }

    fun setSala(sala: Sala) {
        this.sala = sala
    }

   fun setStatusSala(statusSala: Status) {
        this.statusSala = statusSala
   }
    fun getHorario(): LocalTime {
        return horario
    }

    fun setHorario(horario: LocalTime) {
        this.horario = horario
    }
}