interface ArvoreGenealogicaDAO<T> {
    fun obterRaiz(): NoFamiliar<T>?
    fun criarFilho(nomeNo: String, genitor: String, dataNasc: Int, dataFal: Int?)
    fun criarRaiz(nomeNo: String?, dataNasc: Int, dataFal: Int?)
    fun criarConjuge(nomeNo: String, companheiro: String, dataNasc: Int, dataFal: Int?)
    fun removerNo(nome: String): ListaFilhos
    fun buscarNo(nomeNo: String): NoFamiliar<T>?
    fun limpar()
    fun imprimir()
}