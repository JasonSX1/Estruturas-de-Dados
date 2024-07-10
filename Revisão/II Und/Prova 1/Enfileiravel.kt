interface Enfileiravel {

    fun enfileriarInicio(dado: Any?)
    fun enfileirarFim(dado: Any?)
    fun desenfileirarInicio(): Any?
    fun desenfileirarFim(): Any?
    fun atualizarInicio(dado: Any?)
    fun atualizarFim(dado: Any?)
    fun frente(): Any?
    fun tras(): Any?

    //funções auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimirFrentePraTras(): String
    fun imprimirTrasPraFrente(): String
}