fun buscar(dado: T): NoTriplo<T>{
    var noAuxiliar = raiz
    var retorno: NoTriplo<T>

    while(noAuxiliar != null){
        if (dado == noAuxiliar.dado)){
            retorno = noAuxiliar //Retorno o nó que eu encontrei
            break
        } else {
            if(dado as Int < noAuxiliar.dado as Int) //Se o dado que eu estou buscando (que tá no noaux) for menor que o que estou atualmente
                if(noAuxiliar.esquerda != null) //Verifico se a esquerda é nula
                    noAuxiliar = noAuxiliar.esquerda() //Se não for eu vou pra lá e continuo buscando
                else //Caso contrario
                    break //O dado que estou buscando não existe
            else
                //Fazer o caso da direita
                if(noAuxiliar.direita != null)
                    noAuxiliar = noAuxiliar.direita
                else
                    break
        }
    }
    return retorno
}

fun apagar(dado: T) : T{ //Retornando o nó que apaguei
    var noAuxiliar = buscar(dado)
    var retorno: NoTriplo<T>
    //no não encontrado
    if(noAuxiliar == null)
        return null
    //Caso 1: Nó sem filhos
    if(noAuxiliar.esquerda() == null && noAuxiliar.direita() == null)
        apagarNoFolha(NoAuxiliar)
    //Caso 2: Nó com um filho
    else if(noAuxiliar.esquerda == null || noAuxiliar.direita == null)
        apagarComUmFilho(noAuxiliar)
    //Caso 3: Nó com dois filhos
    else
        apagarComDoisFilhos(noAuxiliar)

    return retorno
    //professor fez returnNoAuxiliar e não criou a variavel retorno
    }
}

//O professor gosta de iniciar a variavel de retorno como fácil e somente muda ela se encontrar o dado

//PROFESSOR IMPLEMENTA BUSCAR E EXISTE, ONDE BUSCAR RETORNA O DADO QUE VOCE PASSOU PRA BUSCAR, E O EXISTE SOMENTE
//RETORNA VERDADEIRO OU FALSO

//CRIAÇÃO DAS FUNÇÕES AUXILIARES

//para o algoritmo de apagar nó folha eu chego no nó que quero apagar, daí volto no genitor dele e verifico se o
//nó que quero apagar é referente à esquerda ou à direita e quebro essa referencia

private fun apagarNoFolha(nodo: NoTriplo<T>){
    val pai = nodo.genitor
    if(pai == null)
        raiz = nul //Apagando a raiz, o caso em que o pai do nó que quero apagar é nulo
    else {
        if(nodo==pai.esquerda)
            //nodo é filho pela esquerda
            pai.esquerda = null
        else
            //nodo é filho pela direita
            pai.direita = null
    }
}

//Para apagar um nó com um filho, eu pego o pai do nó que quero apagar e passo a
//referenciar o filho do nó que quero apagar

private fun apagarComUmFilho(nodo: NoTriplo<T>){
    val avo = nodo.genitor
    val neto = (nodo.esquerda != null ? nodo.esquerda : nodo.direita)

    val neto =
        if(nodo.esquerda != null)
            nodo.esquerda
        else
            nodo.direita

    if(avo == null){
        raiz = neto
        raiz.genitor = null
    } else {
        neto.genitor = avo
        if(nodo == avo.esquerda){
            avo.esquerda = neto
        } else {
            avo.direita = neto
        }
    }
}

//ARVORES AVLLLLL

//PARA ROTAÇÕES DUPLAS, A PRIMEIRA ROTAÇÃO É REALIZADA NO FILHO DESBALANCEADO DO NÓ DESBALANCEADO, ENTÃO
//A SEGUNDA ROTAÇÃO É FEITA NO PRÓPRIO NÓ DESBALANCEADO

