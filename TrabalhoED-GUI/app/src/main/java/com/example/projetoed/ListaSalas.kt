package src

class ListaSalas {
    private var salas = arrayOf(Sala(75, 1), Sala(40, 2), Sala(50, 3))

    fun getSala(i :Int): Sala{
        for (j in salas){
            if (i == j.getNumeroSala()){
                return j
            }
        }
        return null!!
    }
    fun printSalas(){
        for (i in salas){
            println("Sala ${i.getNumeroSala()}")
        }
    }

}