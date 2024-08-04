data class NoFamiliar<T>(var dado: Dados) {
    var genitor: NoFamiliar<T>? = null
    var conjuge: NoFamiliar<T>? = null //relação horizontal entre nodos
    var filhos: ListaFilhos? = null//def. de lista de filhos
    //  var relacao? //var parentesco em relação à raiz
    lateinit var dados: Dados
}