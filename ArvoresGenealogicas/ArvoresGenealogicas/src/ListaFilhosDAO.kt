interface ListaFilhosDAO {
    // Metodos principais
    fun anexar(dado: Any?)
    fun apagar(posicao: Int): Any?
    fun apagarTodos(): Array<Any?>
    fun selecionarTodos(): Array<Any?>
    fun limpar() //dentro de apagarTodos usa ele
    fun estaVazia(): Boolean
}