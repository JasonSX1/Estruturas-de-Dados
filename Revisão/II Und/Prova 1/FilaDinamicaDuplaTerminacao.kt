class FilaDinamicaDuplaTerminacao(private val tamanho: Int = 10): Enfileiravel {

    private var ponteiroInicio: NoDuplo? = null
    private var ponteiroFim: NoDuplo? = null
    private var quantidade = 0

    override fun enfileriarInicio(dado: Any?) {
        TODO("Not yet implemented")
    }

    override fun enfileirarFim(dado: Any?) {
        TODO("Not yet implemented")
    }

    override fun desenfileirarInicio(): Any? {
        TODO("Not yet implemented")
    }

    override fun desenfileirarFim(): Any? {
        TODO("Not yet implemented")
    }

    override fun atualizarInicio(dado: Any?) {
        TODO("Not yet implemented")
    }

    override fun atualizarFim(dado: Any?) {
        TODO("Not yet implemented")
    }

    override fun frente(): Any? {
        var dadoAux: Any? = null
        if(!estaVazia())
            dadoAux = ponteiroInicio?.dado
        else
            println("Fila Vazia")

        return dadoAux
    }

    override fun tras(): Any? {
        var dadoAux: Any? = null
        if(!estaVazia())
            dadoAux = ponteiroFim?.dado
        else
            println("Fila Vazia")

        return dadoAux
    }

    override fun estaCheia(): Boolean {
        return quantidade == tamanho
    }

    override fun estaVazia(): Boolean {
        return quantidade == 0
    }

    override fun imprimirFrentePraTras(): String {
        var ponteiroAuxiliar = ponteiroFim
        var resultado = ("[")
        for (i in 0 until quantidade){
            resultado += ponteiroAuxiliar?.dado
            if(i != quantidade-1)
                resultado + " , "

            ponteiroAuxiliar =ponteiroAuxiliar?.anterior
        }
        return "$resultado]"
    }

    override fun imprimirTrasPraFrente(): String {
        var ponteiroAuxiliar = ponteiroInicio
        var resultado = ("[")
        for (i in 0 until quantidade){
            resultado += ponteiroAuxiliar?.dado
            if(i != quantidade-1)
                resultado + " , "

            ponteiroAuxiliar =ponteiroInicio?.proximo
        }
        return "$resultado"
    }


}