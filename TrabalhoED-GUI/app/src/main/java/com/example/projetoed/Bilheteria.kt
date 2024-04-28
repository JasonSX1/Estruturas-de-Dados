package src

class Bilheteria {
    private var custo : Double = 20.0

    private var contagem = mutableMapOf(
        "Inteira" to 0,
        "Meia" to 0,
        "Inteira3D" to 0,
        "Meia3D" to 0
    )
    fun contabilizarFaturamento() : Double{
        var faturamento = 0.0
        for (i in contagem){
            if(i.key == "Inteira"){
                faturamento += i.value * custo
            }
            if(i.key == "Meia"){
                faturamento += i.value * (custo/2)
            }
            if(i.key == "Inteira3D"){
                faturamento += i.value * (custo * 1.25)
            }
            if(i.key == "Meia3D"){
                faturamento += i.value * ((custo * 1.25)/2)
            }

        }
        return faturamento
    }
    fun comprar(sessao: Sessao, poltrona: Int) : Boolean{
        val is3d : Boolean = sessao.get3d()
        if (sessao.getStatusSala() == Status.VENDA) {
            if (is3d) {
                contagem["Inteira3D"] = contagem["Inteira3D"]!! + 1
                println("Compra concluída com sucesso!")
            } else {
                contagem["Inteira"] = contagem["Inteira"]!! + 1
                println("Compra concluída com sucesso!")

            }
            return true
        }
        return false
    }
    fun comprarMeia(sessao: Sessao): Boolean {
        val is3D: Boolean = sessao.get3d()
        if (sessao.getStatusSala() == Status.VENDA) {
            if (is3D) {
                contagem["Meia3D"] = contagem["Meia3D"]!! + 1
                println("Compra concluída com sucesso!")

            } else {
                contagem["Meia"] = contagem["Meia"]!! + 1
                println("Compra concluída com sucesso!")

            }
            return true
        }
        return false
    }
    fun cancelarCompra(sessao: Sessao, poltrona : Int) : Boolean{
        val is3d : Boolean = sessao.get3d()
        if (sessao.getStatusSala() == Status.VENDA) {
            if (is3d) {
                contagem["Inteira3D"] = contagem["Inteira3D"]!! - 1
                println("Compra cancelada com sucesso!")
            } else {
                contagem["Inteira"] = contagem["Inteira"]!! - 1
                println("Compra cancelada com sucesso!")
            }
            return true
        }
        return false
    }
    fun cancelarCompraMeia(sessao: Sessao, poltrona : Int) : Boolean {
        val is3D: Boolean = sessao.get3d()
        if (sessao.getStatusSala() == Status.VENDA) {
            if (is3D) {
                contagem["Meia3D"] = contagem["Meia3D"]!! - 1
                println("Compra concluída com sucesso!")

            } else {
                contagem["Meia"] = contagem["Meia"]!! - 1
                println("Compra concluída com sucesso!")

            }
            return true
        }
        return false
    }
    fun verificacao(sessao: Sessao) : Boolean{
        val sala = sessao.getSala()
        if(sala.isFull()){
            println("src.Sala cheia, escolha outra sessão")
            return false
        }
        return true
    }
    fun iniciarVenda(sessao: Sessao){
        sessao.setStatusSala(Status.VENDA)
    }
    fun encerrarVenda(sessao: Sessao){
        sessao.setStatusSala(Status.FECHADA)
    }
}