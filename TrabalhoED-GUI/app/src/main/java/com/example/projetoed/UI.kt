package src

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class UI {
    private val bilheteria = Bilheteria()
    private val listaSessoes = ListaSessoes()
    private val listaSalas = ListaSalas()
    fun menuInicial() {
        var controle = true
        val login = Login()
        do {
            println("Deseja fazer login?")
            println("1 - Sim, sou administrador")
            println("2 - Não, sou convidado")
            println("3 - Sair")
            val opcao = readlnOrNull()
            when (opcao) {
                "1" -> {
                    do {
                        println("Digite seu usuário")
                        val user = readln()
                        println("Digite sua senha")
                        val senha = readln()

                        if (login.autenticacao(user, senha)) {
                            menuAdmin()
                        } else {
                            println("Usuário ou senha incorretos")
                        }
                    } while (!login.autenticacao(user, senha))
                }

                "2" -> {
                    menuConvidado()
                }

                "3" -> {
                    println("Até mais!")
                    controle = false
                }

                else -> {
                    println("Opção inválida")
                }
            }
        } while (controle)

    }

    private fun menuAdmin() {
            var controle = false
            do {
                println("1 - Adicionar sessão")
                println("2 - Remover sessão")
                println("3 - Editar sessão")
                println("4 - Verificar faturamento")
                println("5 - Iniciar venda de uma sessão")
                println("6 - Encerrar venda e iniciar exibição de uma sessão")
                println("7 - Sair")
                var opcao = readlnOrNull()
                when (opcao) {
                    "1" -> {
                        println("Digite o nome do filme")
                        val nome = readln()
                        var duracao = 0
                        do {
                            println("Digite a duração do filme (em minutos)")
                            try {
                                duracao = readln().toInt()
                                if (duracao <= 0) {
                                    println("Duração inválida")
                                }else{
                                    controle = true
                                }
                            } catch (e: NumberFormatException) {
                                println("Duração inválida")
                            }
                        } while (!controle)

                        var producao = ""
                        do {
                            controle = false
                            println("Informe o tipo de produção:")
                            println("1. Nacional")
                            println("2. Internacional")
                            opcao = readlnOrNull()
                            when (opcao) {
                                "1" -> {
                                    producao = "Nacional"
                                    controle = true
                                }

                                "2" -> {
                                    producao = "Internacional"
                                    controle = true
                                }

                                else -> {
                                    println("Opção inválida. Por favor, selecione uma das opções válidas.")
                                }
                            }
                        } while (!controle)
                        var audio = ""
                        do {

                            controle = false
                            println("Informe o tipo de áudio:")
                            println("1. Original")
                            println("2. Original com legenda")
                            println("3. Dublado")
                            opcao = readlnOrNull()
                            when (opcao) {
                                "1" -> {
                                    audio = "Original"
                                    controle = true
                                }

                                "2" -> {
                                    audio = "Original com legenda"
                                    controle = true
                                }

                                "3" -> {
                                    audio = "Dublado"
                                    controle = true
                                }

                                else -> {
                                    println("Opção inválida. Por favor, selecione uma das opções válidas.")
                                }
                            }
                        } while (!controle)

                        var is3d = false
                        do {
                            controle = false
                            println("Informe se ele é 3D(S/N)")
                            val is3dAux = readln()
                            if (is3dAux != "S" && is3dAux != "N") {
                                println("Opção inválida")
                            } else if (is3dAux == "S") {
                                is3d = true
                                controle = true
                            }else{
                                controle = true
                            }
                        } while (!controle)
                        lateinit var horario: LocalTime
                        lateinit var salaSelecionada: Sala
                        do {
                            controle = false
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val input = readlnOrNull()
                                try {
                                    horario = LocalTime.parse(input.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                    controle = true
                                } catch (e: DateTimeParseException) {
                                    println("Formato inválido. Por favor, digite no formato correto.")
                                }
                            } while (!controle)
                            var numeroSala: Int
                            do {
                                controle = false
                                listaSalas.printSalas()
                                println("Digite o número da sala desejada")
                                try {
                                    numeroSala  = readln().toInt()
                                    if (numeroSala in 1..3) {
                                        salaSelecionada = listaSalas.getSala(numeroSala)
                                        controle = true
                                    }else{
                                        println("Sala inválida")
                                    }
                                } catch (e: NumberFormatException) {
                                    println("Sala inválida")
                                }
                            } while (!controle)
                            controle = listaSessoes.verificacaoHorarioSala(horario, salaSelecionada)
                        } while (!controle)
                        val sessao = Sessao(nome, duracao, producao, audio, is3d, salaSelecionada, horario, Status.ESPERA)
                        listaSessoes.add(sessao)
                        println("Sessão adicionada com sucesso!")
                    }

                    "2" -> {
                        controle = false
                        lateinit var salaSelecionada: Sala
                        lateinit var horario: LocalTime
                        do {
                            if (listaSessoes.estaVazia()){
                                controle = true
                                println("Não existem sessões para serem removidas")
                                break
                            }
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val input = readlnOrNull()
                                try {
                                    horario = LocalTime.parse(input.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                    controle = true
                                } catch (e: DateTimeParseException) {
                                    println("Formato inválido. Por favor, digite no formato correto.")
                                }
                            } while (!controle)
                            var numeroSala: Int?
                            do {
                                controle = false
                                listaSalas.printSalas()
                                println("Digite o número da sala desejada")
                                try {
                                    numeroSala  = readln().toInt()
                                    if (numeroSala <= 0 || numeroSala > 3) {
                                        println("Sala inválida")
                                    }else{
                                        salaSelecionada = listaSalas.getSala(numeroSala)
                                        controle = true
                                    }
                                } catch (e: NumberFormatException) {
                                    println("Sala inválida")
                                }
                            } while (!controle)
                            controle = listaSessoes.verificacaoHorarioSala2(horario, salaSelecionada)
                        } while (!controle)
                        listaSessoes.remove(horario, salaSelecionada)
                        println("Sessão removida com sucesso!")
                    }

                    "3" -> {
                        lateinit var salaTemp: Sala
                        controle = false
                        lateinit var horarioTemp: LocalTime
                        do {
                            if (listaSessoes.estaVazia()){
                                controle = true
                                println("Não existem sessões para serem editadas")
                                break
                            }
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val horario = readlnOrNull()
                                try {
                                    horarioTemp = LocalTime.parse(horario.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                    controle = true
                                } catch (e: DateTimeParseException) {
                                    println("Formato inválido. Por favor, digite no formato correto.")
                                }
                            } while (!controle)
                            var numeroSala: Int?
                            do {
                                controle = false
                                listaSalas.printSalas()
                                println("Digite o número da sala desejada")
                                try {
                                    numeroSala  = readln().toInt()
                                    if (numeroSala <= 0 || numeroSala > 3) {
                                        println("Sala inválida")
                                    }else{
                                        salaTemp = listaSalas.getSala(numeroSala)
                                        controle = true
                                    }
                                } catch (e: NumberFormatException) {
                                    println("Sala inválida")
                                }
                            } while (!controle)
                            controle = listaSessoes.verificacaoHorarioSala2(horarioTemp, salaTemp)
                        } while (!controle)
                        val sessaoAntiga = listaSessoes.getSessao(horarioTemp, salaTemp)
                        println("Digite o nome do filme")
                        val nome = readln()
                        controle = false
                        var duracao = 0
                        do {
                            println("Digite a duração do filme (em minutos)")
                            try {
                                duracao = readln().toInt()
                                if (duracao <= 0) {
                                    println("Duração inválida")
                                }else{
                                    controle = true
                                }
                            } catch (e: NumberFormatException) {
                                println("Duração inválida")
                            }
                        } while (!controle)
                        var producao = ""
                        do {
                            controle = false
                            println("Informe o tipo de produção:")
                            println("1. Nacional")
                            println("2. Internacional")
                            opcao = readlnOrNull()
                            when (opcao) {
                                "1" -> {
                                    producao = "Nacional"
                                    controle = true
                                }

                                "2" -> {
                                    producao = "Internacional"
                                    controle = true
                                }

                                else -> {
                                    println("Opção inválida. Por favor, selecione uma das opções válidas.")
                                }
                            }
                        } while (!controle)
                        var audio = ""
                        do {

                            controle = false
                            println("Informe o tipo de áudio:")
                            println("1. Original")
                            println("2. Original com legenda")
                            println("3. Dublado")
                            opcao = readlnOrNull()
                            when (opcao) {
                                "1" -> {
                                    audio = "Original"
                                    controle = true
                                }

                                "2" -> {
                                    audio = "Original com legenda"
                                    controle = true
                                }

                                "3" -> {
                                    audio = "Dublado"
                                    controle = true
                                }

                                else -> {
                                    println("Opção inválida. Por favor, selecione uma das opções válidas.")
                                }
                            }
                        } while (!controle)

                        var is3d = false
                        do {
                            controle = false
                            println("Informe se ele é 3D(S/N)")
                            val is3dAux = readln()
                            if (is3dAux != "S" && is3dAux != "N") {
                                println("Opção inválida")
                            } else if (is3dAux == "S") {
                                is3d = true
                                controle = true
                            }else{
                                controle = true
                            }
                        } while (!controle)
                        lateinit var horario: LocalTime
                        lateinit var salaSelecionada: Sala
                        do {


                            do {
                                controle = false
                                do {
                                    println("Digite a hora no formato HH:mm:ss")
                                    val input = readlnOrNull()
                                    try {
                                        horario =
                                            LocalTime.parse(input.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                        controle = true
                                    } catch (e: DateTimeParseException) {
                                        println("Formato inválido. Por favor, digite no formato correto.")
                                    }
                                } while (!controle)
                                var numeroSala: Int?
                                do {
                                    controle = false
                                    listaSalas.printSalas()
                                    println("Digite o número da sala desejada")
                                    try {
                                        numeroSala = readln().toInt()
                                        if (numeroSala <= 0 || numeroSala > 3) {
                                            println("Sala inválida")
                                        } else {
                                            salaSelecionada = listaSalas.getSala(numeroSala)
                                            controle = true
                                        }
                                    } catch (e: NumberFormatException) {
                                        println("Sala inválida")
                                    }
                                } while (!controle)
                                controle = listaSessoes.verificacaoHorarioSala(horario, salaSelecionada)
                            } while (!controle)
                            val sessaoAtualizada = Sessao(nome, duracao, producao, audio, is3d, salaSelecionada, horario, Status.ESPERA)
                            controle = listaSessoes.edit(sessaoAntiga, sessaoAtualizada)
                        }while (!controle)
                        println("Sessão editada com sucesso!")
                    }
                    "4" -> {
                        println("Faturamento total: R$ ${bilheteria.contabilizarFaturamento()}")
                    }
                    "5" ->{
                        listaSessoes.printAll()
                        lateinit var horarioTemp : LocalTime
                        lateinit var salaTemp : Sala
                        do {
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val horario = readlnOrNull()
                                do {
                                    try {
                                        horarioTemp =
                                            LocalTime.parse(horario.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                        controle = true
                                    } catch (e: DateTimeParseException) {
                                        println("Formato inválido. Por favor, digite no formato correto.")
                                    }
                                } while (!controle)
                                var numeroSala: Int?
                                do {
                                    controle = false
                                    listaSalas.printSalas()
                                    println("Digite o número da sala desejada")
                                    try {
                                        numeroSala = readln().toInt()
                                        if (numeroSala <= 0 || numeroSala > 3) {
                                            println("Sala inválida")
                                        } else {
                                            salaTemp = listaSalas.getSala(numeroSala)
                                            controle = true
                                        }
                                    } catch (e: NumberFormatException) {
                                        println("Sala inválida")
                                    }
                                } while (!controle)
                                controle = listaSessoes.verificacaoHorarioSala2(horarioTemp, salaTemp)
                            }while(!controle)
                            val sessaoSelecionada = listaSessoes.getSessao(horarioTemp, salaTemp)
                            bilheteria.iniciarVenda(sessaoSelecionada)
                        } while (!controle)
                    }
                    "6" ->{
                        listaSessoes.printAll()
                        lateinit var horarioTemp : LocalTime
                        lateinit var salaTemp : Sala
                        do {
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val horario = readlnOrNull()
                                do {
                                    try {
                                        horarioTemp =
                                            LocalTime.parse(horario.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                        controle = true
                                    } catch (e: DateTimeParseException) {
                                        println("Formato inválido. Por favor, digite no formato correto.")
                                    }
                                } while (!controle)
                                var numeroSala: Int?
                                do {
                                    controle = false
                                    listaSalas.printSalas()
                                    println("Digite o número da sala desejada")
                                    try {
                                        numeroSala = readln().toInt()
                                        if (numeroSala <= 0 || numeroSala > 3) {
                                            println("Sala inválida")
                                        } else {
                                            salaTemp = listaSalas.getSala(numeroSala)
                                            controle = true
                                        }
                                    } catch (e: NumberFormatException) {
                                        println("Sala inválida")
                                    }
                                } while (!controle)
                                controle = listaSessoes.verificacaoHorarioSala2(horarioTemp, salaTemp)
                            }while(!controle)
                            val sessaoSelecionada = listaSessoes.getSessao(horarioTemp, salaTemp)
                            bilheteria.encerrarVenda(sessaoSelecionada)
                        } while (!controle)
                    }
                    "7" -> {
                        println("Até mais!")
                        controle = false
                    }
                    else -> {
                        println("Opção inválida")
                    }
                }
            }while (controle)
    }

    private fun menuConvidado(){
        var controle = true
        do {
            println("1 - Verificar sessões disponíveis")
            println("2 - Comprar ingresso")
            println("3 - Sair")
            var opcao = readlnOrNull()
            when (opcao) {
                "1" -> {
                    listaSessoes.printEmVenda()
                }
                "2" -> {
                    println("Você deseja comprar meia entrada? (S/N)")
                    opcao = readln()

                    lateinit var horarioTemp : LocalTime
                    lateinit var salaTemp : Sala
                    when (opcao) {
                        "S" -> {
                            listaSessoes.printEmVenda()
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val horario = readlnOrNull()
                                do {
                                    try {
                                        horarioTemp = LocalTime.parse(horario.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                        controle = true
                                    } catch (e: DateTimeParseException) {
                                        println("Formato inválido. Por favor, digite no formato correto.")
                                    }
                                } while (!controle)
                                var numeroSala: Int?
                                do {
                                    controle = false
                                    listaSalas.printSalas()
                                    println("Digite o número da sala desejada")
                                    try {
                                        numeroSala  = readln().toInt()
                                        if (numeroSala <= 0 || numeroSala > 3) {
                                            println("Sala inválida")
                                        }else{
                                            salaTemp = listaSalas.getSala(numeroSala)
                                            controle = true
                                        }
                                    } catch (e: NumberFormatException) {
                                        println("Sala inválida")
                                    }
                                } while (!controle)
                                controle = listaSessoes.verificacaoHorarioSala2(horarioTemp, salaTemp)
                                val sessaoSelecionada = listaSessoes.getSessao(horarioTemp, salaTemp)
                                if (bilheteria.verificacao(sessaoSelecionada)){
                                    bilheteria.comprarMeia(sessaoSelecionada)
                                }else{
                                    println("Sessão cheia, escolha outra sessão")
                                    controle = false
                                }
                            } while (!controle)
                        }
                        "N" -> {
                            listaSessoes.printAll()
                            do {
                                println("Digite a hora no formato HH:mm:ss")
                                val horario = readlnOrNull()
                                do {
                                    try {
                                        horarioTemp = LocalTime.parse(horario.toString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
                                        controle = true
                                    } catch (e: DateTimeParseException) {
                                        println("Formato inválido. Por favor, digite no formato correto.")
                                    }
                                } while (!controle)
                                var numeroSala: Int?
                                do {
                                    controle = false
                                    listaSalas.printSalas()
                                    println("Digite o número da sala desejada")
                                    try {
                                        numeroSala  = readln().toInt()
                                        if (numeroSala <= 0 || numeroSala > 3) {
                                            println("Sala inválida")
                                        }else{
                                            salaTemp = listaSalas.getSala(numeroSala)
                                            controle = true
                                        }
                                    } catch (e: NumberFormatException) {
                                        println("Sala inválida")
                                    }
                                } while (!controle)
                                controle = listaSessoes.verificacaoHorarioSala2(horarioTemp, salaTemp)

                            } while (!controle)
                            val sessaoSelecionada = listaSessoes.getSessao(horarioTemp, salaTemp)
                            if (bilheteria.verificacao(sessaoSelecionada)){
                                bilheteria.comprar(sessaoSelecionada)
                            }else{
                                println("Sessão cheia, escolha outra sessão")
                                controle = false
                            }
                        }
                        else -> {
                            println("Opção inválida")
                        }
                    }

                }
                "3" -> {
                    println("Até mais!")
                    controle = false
                }
                else -> {
                    println("Opção inválida")
                }
            }
        } while (controle)
    }
}