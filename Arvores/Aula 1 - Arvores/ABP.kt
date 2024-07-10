Class ABP: Arborizavel {

    private var raiz: NoTriplo? Null


    //FINALIZAR ESSA IMPLEMENTAÇÃAAAO
    override fun inserir (dado: Any?){
        var novoNo = NoTriplo(dado)

        if (raiz == null){
            raiz = novoNo
        } else {
            var noAuxiliar = raiz
            while(noAuxiliar != null) {
                if (dado< noAuxiliar.dado) {
                    //preciso ir pra esquerdo
                    if(noAuxiliar.esquerda != null){
                        noAuxiliar = noAuxiliar.esquerda
                } else {
                    //insiro o dado aqui
                    noAuxiliar.esquerda = novoNo
                    novoNo.genitor = noAuxiliar
                    break //Como o noAuxiliar não é setado em nenhum momento como nulo, é preciso ter o break para interromper o loop de execução
                }
            } else {
                //preciso ir para a direita
                if (noAuxiliar.direita != null)

                }            }
        }
    }

    Override fun imprimirOrdem()
}