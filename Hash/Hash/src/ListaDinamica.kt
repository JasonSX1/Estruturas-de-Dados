class ListaDinamica(private val tamanho: Int = 10) : Listavel {

    private var ponteiroInicio: NoDuplo? = null
    private var ponteiroFim: NoDuplo? = null //tenho que dizer o tipo do dado recebido
    private var quantidade = 0

    override fun estaCheia(): Boolean {
        return quantidade == tamanho
    }

    override fun estaVazia(): Boolean {
        return quantidade == 0
    }

    override fun anexar(dado: Any?) { //Anexar consiste em inserir no final
        if (!estaCheia()) {
            val noTemp = NoDuplo(dado)
            //noTemp.dado = dado
            noTemp.anterior = ponteiroFim
            if (!estaVazia())
                ponteiroFim?.proximo = noTemp
            else
                ponteiroInicio = noTemp

            ponteiroFim = noTemp
            quantidade = quantidade.inc()
        } else {
            println("Lista Cheia!")
        }
    }

    override fun selecionarTodos(): Array<Any?> { //não tem nenhum problema na utilização do array nesse caso, já que ele é um array criado na hora só pra retornar a string de ImprimirTodos
        var dadosAux: Array<Any?> = arrayOfNulls(quantidade) //aqui é um exemplo de necessidade de especificar o tipo do dado, que foi passado Array<Any?>
        if (!estaVazia()) {
            var ponteiroAuxiliar = ponteiroInicio
            for (i in 0 until quantidade) {
                dadosAux[i] = ponteiroAuxiliar?.dado //o .dado é a mesma coisa que um getDado
                ponteiroAuxiliar = ponteiroAuxiliar?.proximo
            }
        } else {
            println("Lista Vazia!")
        }
        return dadosAux
    }

    override fun selecionar(posicao: Int): Any? {
        var dadoAux: Any? = null
        if (!estaVazia()) {
            if (posicao >= 0 && posicao < quantidade) { //verificacao se a posicao é valida
                //isso é adaptavel, se eu quiser que a minha lista comece em 2 por exemplo, ficaria (posicao>=0 && posicao < quantidade-2), ou se eu quiser usar com os indices do array correpondentes aos valores reais,
                //ficaria (posicao >=1 && posicao <= quantidade) ou ...&& posicao < quantidade+1
                var ponteiroAux = ponteiroInicio
                for (i in 0 until posicao) { //laço para percorrer a ED e alncançar o elemento a ser selecionado
                    ponteiroAux = ponteiroAux?.proximo

                    dadoAux = ponteiroAux?.dado //final da seleção do dado desejado
                }
            } else {
                println("Posição Inválida")
            }
        } else {
            println("Lista Vazia!")
        }
        return dadoAux
    }

    override fun atualizar(posicao: Int, dado: Any?) {
        if (!estaVazia()) {
            if (posicao >= 0 && posicao < quantidade) {
                var ponteiroAux = ponteiroInicio
                for (i in 0 until posicao)
                    ponteiroAux = ponteiroAux?.proximo
                /////////////////////////////
                ponteiroAux?.dado = dado
            } else {
                println("Posição Inválida")
            }
        } else {
            println("Lista Vazia!")
        }
    }

    override fun apagar(posicao: Int): Any? {
        var dadoAux: Any? = null
        if (!estaVazia()) {
            if (posicao >= 0 && posicao < quantidade) {
                var ponteiroAuxiliar = ponteiroInicio
                for (i in 0 until posicao)
                    ponteiroAuxiliar = ponteiroAuxiliar?.proximo

                dadoAux = ponteiroAuxiliar?.dado

                val ponteiroAnterior = ponteiroAuxiliar?.anterior
                val ponteiroProximo  = ponteiroAuxiliar?.proximo

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
        } else {
            println("Lista Vazia!")
        }
        return dadoAux
    }

    override fun apagarTodos(): Array<Any?> {
        var dadosAux: Array<Any?> = selecionarTodos()
        limpar()
        return dadosAux
    }

    override fun limpar() {
        ponteiroInicio = null
        ponteiroFim = null
        quantidade = 0
    }

    override fun tamanho(): Int {
        return quantidade
    }

    override fun inserir(posicao: Int, dado: Any?) {
        if (!estaCheia()) {
            if (posicao >= 0 && posicao <= quantidade) {
                val noTemp = NoDuplo(dado)
                /*
                var ponteiroAuxiliar = ponteiroInicio
                for (i in 0 until posicao)
                ponteiroAuxiliar = ponteiroAuxiliar?.proximo
                ///////////////////////////////
                val ponteiroProximo = ponteiroAuxiliar
                val ponteiroAnterior = ponteiroFim
                if (ponteiroAuxiliar != null)
                    ponteiroAnterior = ponteiroAuxiliar.anterior
                */ //Este método de inserir deve funcionar da mesma forma que o outro
                var ponteiroAnterior: NoDuplo? = null
                var ponteiroProximo = ponteiroInicio

                for (i in 0 until posicao) {
                    ponteiroAnterior = ponteiroProximo
                    ponteiroProximo = ponteiroProximo?.proximo
                }

                //todas insercoes, exceto inicio
                if (ponteiroAnterior != null)
                    ponteiroAnterior.proximo = noTemp
                else	//insercao no inicio
                    ponteiroInicio = noTemp

                //todas insercoes, exceto fim
                if (ponteiroProximo != null)
                    ponteiroProximo.anterior = noTemp
                else	//insercao no fim
                    ponteiroFim = noTemp

                noTemp.proximo = ponteiroProximo
                noTemp.anterior = ponteiroAnterior

                quantidade = quantidade.inc()
            } else {
                println("Indice Inválido!")
            }
        } else {
            println("Lista Cheia!")
        }
    }

    override fun imprimir(): String {
        var ponteiroAuxiliar = ponteiroInicio
        var resultado = "["
        for (i in 0 until quantidade) {
            resultado += ponteiroAuxiliar?.dado
            if (i != quantidade-1)
                resultado += ","

            ponteiroAuxiliar = ponteiroAuxiliar?.proximo
        }
        return "$resultado]"
    }

}
