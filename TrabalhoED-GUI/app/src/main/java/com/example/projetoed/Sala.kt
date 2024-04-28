package src

data class Sala(private var capacidade: Int, private var numeroSala: Int ) {
    private var numeroPoltronas : Array<Int?> = arrayOfNulls(capacidade)
    private var poltronasUtilizadas : Int = 0


    fun printDisponibilidade(){
        println("Poltronas disponiveis:")
        for (i in 0 until capacidade){
            if(numeroPoltronas[i] == null){
                val aux = i+1
                if (i == capacidade-1){
                    print(" $aux.")
                }else{
                    print(" $aux,")
                }
            }
        }
        println("Capacidade máxima da sala $numeroSala:$capacidade pessoas")
    }
    fun estaDisponivel(poltrona: Int) : Boolean{
        return numeroPoltronas[poltrona-1] == null
    }
    fun isFull(): Boolean{
        return(poltronasUtilizadas == capacidade)
    }

    fun printTaxa(){
        val taxaOcupacao = poltronasUtilizadas.toDouble() / capacidade * 100
        println("Taxa de ocupação da sala: $taxaOcupacao%")
    }

    fun getCapacidade(): Int{
        return capacidade
    }

    fun getNumeroSala() : Int{
        return numeroSala
    }

    fun ocuparPoltrona(poltrona: Int){//colocar no comprar()
        numeroPoltronas[poltrona-1] = poltrona
    }

    fun desocuparPoltrona(poltrona: Int){//colocar no cancelarCompra()
        numeroPoltronas[poltrona-1] = null
    }
}