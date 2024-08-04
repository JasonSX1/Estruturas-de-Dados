fun main() {
    println("Bem-vindo ao sistema de árvores genealógicas!")
    val listaAux = ArvoreGenealogica<String>()
    do {
        println("O que você deseja fazer?")
        println("1 - Criar uma nova árvore genealógica(uma por vez)")
        println("2 - Adicionar uma pessoa à árvore genealógica")
        println("3 - Remover uma pessoa da árvore genealógica")
        println("4 - Visualizar detalhes de uma pessoa na árvore genealógica")
        println("5 - Imprimir a árvore genealógica")
        println("6 - Limpar a árvore genealógica")
        println("7 - Sair")
        val escolha = readLine()!!.toInt()

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
                println(listaAux.obterRaiz())
                listaAux.imprimir()
            }
            2 -> {
                println(listaAux.obterRaiz())
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
                do {
                    println("Digite o nome da pessoa relacionada a pessoa que deseja adicionar:")
                    val nomeRelacionado = readLine()!!
                    val verificacao = listaAux.buscarNo(nomeRelacionado)
                    if (nomeRelacionado == "0") {
                        break
                    }
                    if (verificacao != null) {
                        do {
                            println("Qual a relação da pessoa adicionada com a escolhida?")
                            println("1 - Cônjuge")
                            println("2 - Filho")
                            val escolhaRelacao = readLine()!!
                            if (escolhaRelacao == "1") {
                                listaAux.criarConjuge(nome, nomeRelacionado, dataNasc, dataFal)
                            } else if (escolhaRelacao == "2") {
                                listaAux.criarFilho(nome, nomeRelacionado, dataNasc, dataFal)
                            } else {
                                println("Opção inválida. Por favor, selecione uma opção entre 1 e 2.")
                            }
                        }while (escolhaRelacao != "1" && escolhaRelacao != "2")
                    }else{
                        println("Pessoa não encontrada.")
                        println("Se quiser desistir de adicionar a pessoa, digite '0'.")
                    }
                }while (verificacao == null)
                println(listaAux.obterRaiz())
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