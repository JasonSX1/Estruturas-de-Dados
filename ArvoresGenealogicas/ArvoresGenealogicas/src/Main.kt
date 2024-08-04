fun main() {
    println("Bem-vindo ao sistema de árvores genealógicas!")
    do {
        println("O que você deseja fazer?")
        println("1 - Criar uma nova árvore genealógica(uma por vez)")
        println("2 - Adicionar uma pessoa à árvore genealógica")
        println("3 - Remover uma pessoa da árvore genealógica")
        println("4 - Buscar uma pessoa na árvore genealógica")
        println("5 - Imprimir a árvore genealógica")
        println("6 - Limpar a árvore genealógica")
        println("7 - Sair")
        val escolha = readLine()!!.toInt()
        val listaAux = ArvoreGenealogica<String>()
        when (escolha) {
            1 -> {
                println("Digite o nome da pessoa que irá ser a raiz: ")
                val raiz = readLine()
                println("Digite o ano de nascimento da pessoa que irá ser a raiz: ")
                val dataNasc = readLine()!!.toInt()
                println("A pessoa está morta? (S/N)")
                val morta = readLine()
                var dataFal: Int? = null
                if (morta == "S" || morta == "s") {
                    println("Digite o ano de falecimento da pessoa que irá ser a raiz(): ")
                    dataFal = readLine()!!.toInt()
                }
                listaAux.criarRaiz(raiz, dataNasc, dataFal)
                listaAux.imprimir()
            }
            2 -> {
                println("Digite o nome da pessoa que deseja adicionar:")
                val nome = readLine()!!
                println("Digite a data de nascimento da pessoa que deseja adicionar:")
                val dataNasc = readLine()!!.toInt()
                println("A pessoa está morta? (S/N)")
                val morta = readLine()
                var dataFal: Int? = null
                if (morta == "S" || morta == "s") {
                    println("Digite a data de falecimento da pessoa que irá ser a raiz(): ")
                    dataFal = readLine()!!.toInt()
                }
                println("Digite o nome do genitor da pessoa que deseja adicionar:")
                val genitor = readLine()!!
                val verificacao = listaAux.buscarNo(genitor)
                if (verificacao != null) {
                    listaAux.criarFilho(nome, genitor, dataNasc, dataFal)
                } else {
                    println("Genitor não encontrado.")
                }
                listaAux.imprimir()
            }
            3 -> {
                println("Digite o nome da pessoa que deseja adicionar:")
                listaAux.remover("nome")

            }
            4 -> {
                println("Digite o nome da pessoa que deseja buscar:")
                val nome = readLine()!!
                val no = listaAux.buscarNo(nome)
                if (no != null) {
                    println("Nome: ${no.dado.nome}")
                    println("Ano de nascimento: ${no.dado.anoNas}")
                    if (no.dado.anoFal != null) {
                        println("Ano de falecimento: ${no.dado.anoFal}")
                    }
                } else {
                    println("Pessoa não encontrada.")
                }
            }
            5 -> {
                listaAux.imprimir()
            }
            6 -> {
                listaAux.limpar()
            }
            7 -> {
                println("Obrigado por usar o sistema de árvores genealógicas!")
            }
            else -> {
                println("Opção inválida. Por favor, selecione uma opção entre 1 e 7.")
            }
        }
    }while (escolha != 7)
}