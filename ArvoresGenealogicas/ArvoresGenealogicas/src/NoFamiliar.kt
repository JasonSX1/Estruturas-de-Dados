data class NoFamiliar<T>(var dado: Dados) {
    var conjuge: NoFamiliar<T>? = null //relação horizontal entre nodos
    var filhos: ListaFilhos? = null //def. de lista de filhos
}