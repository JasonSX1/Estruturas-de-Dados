class ListaFilhos : ListaFilhosDAO {
    private var ponteiroInicio: NoLista? = null
    private var ponteiroFim: NoLista? = null
    private var quantidade = 0

    //idêntico ao enfileirar de FilaDinamica
    override fun anexar(dado: Any?) {
        val noTemp = NoLista(dado)
        //noTemp.dado = dado
        noTemp.anterior = ponteiroFim
        if (!estaVazia())
            ponteiroFim?.proximo = noTemp
        else
            ponteiroInicio = noTemp

        ponteiroFim = noTemp
        quantidade = quantidade.inc()
    }

    override fun limpar() {
        ponteiroInicio = null
        ponteiroFim = null
        quantidade = 0
    }

    override fun apagarTodos(): Array<Any?> {
        var dadosAux: Array<Any?> = selecionarTodos()
        limpar()
        return dadosAux
    }

    override fun selecionarTodos(): Array<Any?> {
        var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
        var ponteiroAuxiliar = ponteiroInicio
        for (i in 0 until quantidade) {
            dadosAux[i] = ponteiroAuxiliar?.dado
            ponteiroAuxiliar = ponteiroAuxiliar?.proximo
        }

        return dadosAux
    }

    override fun apagar(posicao: Int): Any? {
        var dadoAux: Any? = null
        if (posicao >= 0 && posicao < quantidade) {
            ////////////////////////////////
            //Codigo de posicionamento do ponteiro auxiliar, no nodo
            //que será feita alguma operação. Esse codigo é o mesmo
            //para os metodos update, delete, select e insert
            var ponteiroAuxiliar = ponteiroInicio
            for (i in 0 until posicao)
                ponteiroAuxiliar = ponteiroAuxiliar?.proximo
            ///////////////////////////////
            dadoAux = ponteiroAuxiliar?.dado

            val ponteiroAnterior = ponteiroAuxiliar?.anterior
            val ponteiroProximo = ponteiroAuxiliar?.proximo

            if (ponteiroAnterior != null)
                ponteiroAnterior.proximo = ponteiroProximo
            //remocao do inicio, joga o ponteiro de inicio para o proximo nodo.
            else
                ponteiroInicio = ponteiroInicio?.proximo

            if (ponteiroProximo != null)
                ponteiroProximo.anterior = ponteiroAnterior
            //remocao do fim, joga o ponteiro de fim para o nodo anterior.
            else
                ponteiroFim = ponteiroFim?.anterior

            quantidade = quantidade.dec()
        } else {
            println("Indice Inválido!")
        }
        return dadoAux
    }

    override fun estaVazia(): Boolean {
        return quantidade == 0
    }

    override fun imprimir(): String {
        val a = "a"
        return (a)
    }
}