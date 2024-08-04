class ArvoreGenealogica<T>: ArvoreGenealogicaDAO<T>{

    private var raiz: NoFamiliar<T>? = null

    override fun obterRaiz(): NoFamiliar<T>? {
        return raiz

    }

    override fun criarFilho(nomeNo: String, genitor: String, dataNasc: Int, dataFal: Int?) {
        val noGenitor = buscarNo(genitor) ?: return
        val dadosNo = Dados(nomeNo, dataNasc, dataFal)
        val novoNo = NoFamiliar<T>(dadosNo)
        if (noGenitor.filhos == null) {
            noGenitor.filhos = ListaFilhos()
        }
        noGenitor.filhos?.anexar(novoNo)
    }

    override fun criarRaiz(nomeNo: String?, dataNasc: Int, dataFal: Int?) {
        if (nomeNo != null) {
            val dadosNo = Dados(nomeNo, dataNasc, dataFal)
            raiz = NoFamiliar(dadosNo)
        } else {
            println("Nome do nó raiz não pode ser nulo.")
        }
    }


    override fun criarConjuge(nomeNo: String, companheiro: String, dataNasc: Int, dataFal: Int?){
        val noCompanheiro = buscarNo(companheiro) ?: return
        val dadosNo = Dados(nomeNo, dataNasc, dataFal)
        val novoConjuge = NoFamiliar<T>(dadosNo)
        noCompanheiro.conjuge = novoConjuge
    }

    override fun limpar() {
        raiz = null
    }

    override fun imprimir(){
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

    override fun buscarNo(nomeNo: String): NoFamiliar<T>? {
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

    override fun removerNo(nome: String): List<NoFamiliar<T>> {
        val removidos = mutableListOf<NoFamiliar<T>>()
        if (raiz == null) return removidos

        // Caso especial: remoção da raiz
        if (raiz?.dado?.nome == nome) {
            removidos.add(raiz!!)
            raiz = null
            return removidos
        }

        // Chama a função recursiva para remover o nó e seus descendentes
        removerNoRecursivamente(raiz, nome, removidos)
        return removidos
    }

    private fun removerNoRecursivamente(no: NoFamiliar<T>?, nome: String, removidos: MutableList<NoFamiliar<T>>): Boolean {
        if (no == null) return false

        val filhos = no.filhos?.selecionarTodos() ?: emptyArray()
        for (i in filhos.indices) {
            val filho = filhos[i] as NoFamiliar<T>
            if (filho.dado.nome == nome) {
                // Adiciona o nó encontrado na lista de removidos
                removidos.add(filho)
                // Remove o nó da lista de filhos do pai
                no.filhos?.apagar(i)
                return true
            } else if (removerNoRecursivamente(filho, nome, removidos)) {
                return true
            }
        }

        // Verifica o cônjuge
        val conjuge = no.conjuge
        if (conjuge != null && conjuge.dado.nome == nome) {
            // Adiciona o cônjuge na lista de removidos
            removidos.add(conjuge)
            // Remove o cônjuge
            no.conjuge = null
            return true
        }

        return false
    }

    fun imprimirArvoreComRemovidos(no: NoFamiliar<T>?, prefixo: String = "", isUltimo: Boolean = true, removidos: List<NoFamiliar<T>>) {
        if (no == null) return

        // Verifica se o nó está na lista de removidos
        val marcacaoRemovido = if (removidos.contains(no)) "(removido)" else ""

        println("${prefixo}${if (isUltimo) "└── " else "├── "}${no.dado.nome} $marcacaoRemovido")

        val filhos = no.filhos?.selecionarTodos() ?: emptyArray()
        val conjuge = no.conjuge
        val novosPrefixos = prefixo + if (isUltimo) "    " else "│   "

        for (i in filhos.indices) {
            imprimirArvoreComRemovidos(filhos[i] as NoFamiliar<T>, novosPrefixos, i == filhos.lastIndex && conjuge == null, removidos)
        }

        conjuge?.let {
            imprimirArvoreComRemovidos(it, prefixo, false, removidos)
        }
    }
}