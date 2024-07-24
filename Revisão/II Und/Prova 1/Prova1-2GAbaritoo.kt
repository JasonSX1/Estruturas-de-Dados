//Inserir um dado no inicio de uma lista dinamica:

Class testeAvaliação{

    private var ponteiroInicio: NoDuplo<Any>? = null
    private var ponteiroFim: NoDuplo<Any>? = null
    private var quantidade = 0;

    fun adicionarInicio(dado: Any?) {
        val noTemp = NoDuplo(dado)
            if (!estaVazia()) {
                noTemp.proximo = ponteiroInicio
                ponteiroInicio?.anterior = noTemp
            } else {
                ponteiroFim = noTemp
            }
            ponteiroInicio = noTemp
            quantidade.inc();
    }

    fun sort(){
        if(quantidade >1){
            for(i in 0 until (quantidade -1)){
                var atual = ponteiroInicio
                var proximo = atual?.proximo
                for (j in 0 until (quantidade - 1 - i)){
                    if (atual?.dado as Int > proximo?.dado as Int){
                        val aux = atual.dado
                        atual.dado = proximo.dado
                        proximo.dado = aux
                    }
                    atual = atual.proximo
                    proximo = atual?.proximo
                }
            }
        }
    }

    fun insertBegin(data: Any) {
        if (!estaCheia()) {
            val novoNodo = NoDuplo(data)
            novoNodo.proximo = ponteiroInicio
            if (!estaVazia()) {
                ponteiroInicio?.anterior = novoNodo
            } else {
                ponteiroFim = novoNodo
            }
            ponteiroInicio = novoNodo
            quantidade++
        } else {
            println("List is full!")
        }
    }

    fun isPalindrome(data: String): Boolean {
        var retorno = true
        val p = PilhaDinamica<Char>(100)
        for (i in 0 until (data.length / 2)) {
            p.empilhar(data[i])
        }
        for (i in (data.length / 2) until data.length) {
            val parte1 = p.desempilhar()
            val parte2 = data[i]
            if (parte1 != parte2) {
                retorno = false
                break
            }
        }
        return retorno
    }

}