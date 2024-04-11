fun main() {
    var pilha: Empilhavel = PilhaDinamica(7) // sem a declaração de var pilha: Empilhavel eu deixo o codigo inferir o tipo
    //automaticametnte, mas quando explicito o tipo, ele é criado como empilhavelk,
    //implemetntando para a interface
    pilha.empilhar("Instituto")
    pilha.empilhar("Federal")
    println("Topo: ${pilha.espiar()}")
    pilha.empilhar("de")
    pilha.empilhar("Educação")
    pilha.empilhar("Ciência")
    pilha.empilhar("e")
    println("Topo: ${pilha.espiar()}")
    val conteudo = pilha.desempilhar()
    pilha.desempilhar()
    pilha.empilhar("Tecnologia")
    pilha.empilhar("da")
    pilha.empilhar("Bahia")
    pilha.empilhar(pilha.desempilhar())
    pilha.empilhar(conteudo)
    println("Pilha=${pilha.imprimir()}")
}