class ArvoreGenealogica<T>{

    private var raiz: NoFamiliar<T>? = null

    fun obterRaiz(): NoFamiliar<T>? {
        return raiz

    }
    fun criarFilho(nomeNo: String, genitor: String, dataNasc: Int, dataFal: Int?) {
        val noGenitor = buscarNo(genitor) ?: return
        val dadosNo = Dados(nomeNo, dataNasc, dataFal)
        val novoNo = NoFamiliar<T>(dadosNo)
        if (noGenitor.filhos == null) {
            noGenitor.filhos = ListaFilhos()
        }
        noGenitor.filhos?.anexar(novoNo)
    }

    fun criarRaiz(nomeNo: String?, dataNasc: Int, dataFal: Int?) {
        if (nomeNo != null) {
            val dadosNo = Dados(nomeNo, dataNasc, dataFal)
            raiz = NoFamiliar(dadosNo)
        } else {
            println("Nome do nó raiz não pode ser nulo.")
        }
    }


    fun criarConjuge(nomeNo: String, companheiro: String, dataNasc: Int, dataFal: Int?){
        val noCompanheiro = buscarNo(companheiro) ?: return
        val dadosNo = Dados(nomeNo, dataNasc, dataFal)
        val novoConjuge = NoFamiliar<T>(dadosNo)
        noCompanheiro.conjuge = novoConjuge
    }

    fun limpar() {
        raiz = null
    }

    fun imprimir(){
        imprimirArvore(raiz)
    }

    private fun imprimirArvore(no: NoFamiliar<T>?, prefixo: String = "", isUltimo: Boolean = true) {
        if (no == null) return

        println("${prefixo}${if (isUltimo) "└── " else "├── "}${no.dado.nome}")

        val filhos = no.filhos?.selecionarTodos()?.filterIsInstance<NoFamiliar<T>>() ?: emptyList()
        val conjuge = no.conjuge
        val novosPrefixos = prefixo + if (isUltimo) "    " else "│   "

        for (i in filhos.indices) {
            imprimirArvore(filhos[i], novosPrefixos, i == filhos.lastIndex && conjuge == null)
        }

        conjuge?.let {
            imprimirArvore(it, prefixo, false)
        }
    }

    fun buscarNo(nomeNo: String): NoFamiliar<T>? {
        val fila = ListaFilhos()
        fila.anexar(raiz)
        while (!fila.estaVazia()) {
            val noAtual = fila.apagar(0) as? NoFamiliar<T>
            if (noAtual?.dado?.nome == nomeNo) return noAtual

            noAtual?.filhos?.selecionarTodos()?.forEach {
                fila.anexar(it)
            }
            noAtual?.conjuge?.let {
                fila.anexar(it)
            }
        }
        return null
    }

    fun remover(nomeNo: String): Boolean {
        TODO("Not yet implemented")
    }
}