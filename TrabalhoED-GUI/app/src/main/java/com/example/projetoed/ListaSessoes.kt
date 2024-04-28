package src

import java.time.Duration
import java.time.LocalTime

class ListaSessoes{
    private var tamanho : Int = 15
    private var sessoes: Array<Sessao?> = arrayOfNulls(tamanho)
    private var ponteiro : Int = 0

    fun add(sessao: Sessao): Boolean {
        if (ponteiro < tamanho) {
            sessoes[ponteiro++] = sessao
            return true
        } else {
            println("Não existe espaço para sessões")
            return false
        }
    }
    fun remove(horario : LocalTime, sala: Sala) : Boolean{
        if (ponteiro != 0){
            for (i in 0 until  ponteiro){
                if (sessoes[i]?.getHorario()  == horario && sessoes[i]?.getSala()  == sala){
                    sessoes[i] = sessoes[ponteiro]
                    sessoes[ponteiro] = null
                    ponteiro--
                    break
                }
                if(i==ponteiro){
                    println("Sessão Inválida, insira uma sessão válida")
                    return false
                }
            }
        }else{
            println("Não existem filmes diponiveis para serem removidos")
        }
        return true
    }
//    fun edit(sessaoAntiga: Sessao, sessaoAtualizada: Sessao): Boolean {
//        if (sessaoAntiga.getStatusSala() != Status.ESPERA) {
//            println("Sessão não pode ser editada, pois já foi iniciada")
//            return false
//        } else if (ponteiro != 0) {
//            for (i in 0 .. ponteiro) {
//                if (sessoes[i] == sessaoAntiga) {
//                    sessoes[i] = sessaoAtualizada
//                    return true
//                }
//            }
//        } else {
//            println("Não existem filmes disponíveis para serem editados")
//        }
//        return false
//    }
    fun edit(sessaoAntiga: Sessao, sessaoAtualizada: Sessao): Boolean {
    for (i in 0 until ponteiro) {
        if (sessoes[i] == sessaoAntiga) {
            sessoes[i] = sessaoAtualizada
            return true // Objeto substituído com sucesso
        }
    }
    println("Sessão antiga não encontrada") // Mensagem informando que a sessão antiga não foi encontrada
    return false // Objeto antigo não encontrado no array
    }

    fun verificacaoHorarioSala(horario : LocalTime, sala: Sala) : Boolean{
        for (i in 0 until ponteiro) {
            if (sessoes[i] != null) {
                val diferenca = Duration.between(sessoes[i]!!.getHorario(), horario).abs()
                if (sessoes[i]!!.getHorario() == horario && sessoes[i]!!.getSala() == sala) {
                    println("Horário e sala já ocupados")
                    return false
                } else if (diferenca.toMinutes() <= 20) {
                    println("O horário está dentro de um intervalo de até 20 minutos com outra sessão, portanto é inválido.")
                    return false
                }
            }
        }
        return true
    }

        fun verificacaoHorarioSala2(horario : LocalTime, sala: Sala) : Boolean{
        for (i in 0 until  ponteiro){
            if (sessoes[i] != null) {
                if (sessoes[i]?.getHorario() == horario && sessoes[i]?.getSala()  == sala){
                    return true
                }
            }
        }
        if (ponteiro == 0){
            println("Sessão Inválida, insira uma sessão válida")
        }
        return false
    }

    fun getSessao(horario: LocalTime, sala: Sala) : Sessao {
        for (i in sessoes.indices){
            if (sessoes[i] != null) {
                if (sessoes[i]!!.getHorario() == horario && sessoes[i]!!.getSala()  == sala){
                    return sessoes[i]!!
                }else if (sessoes[i] == sessoes[ponteiro]){
                    println("Sessão Inválida, insira uma sessão válida")
                }
            }
        }
        val sessao = Sessao("null", 0, "null", "null", false, Sala(0, 0), LocalTime.of(0, 0), Status.ESPERA)
        return sessao
    }
    fun printAll(){
        for (i in 0 until  ponteiro){
            sessoes[i]?.printSessao()
        }
    }
    fun printEmVenda(){
        for (i in 0 until  ponteiro){
            if (sessoes[i]!!.getStatusSala() == Status.VENDA){
                sessoes[i]?.printSessao()
            }
        }
    }

    fun estaVazia() : Boolean {
        if (ponteiro == 0){
            return true
        }
        return false
    }
}

