class PilhaDinamica(var tamanho: Int = 10): Empilhavel{ //o professor usa também um construtor padrão para definir o tamanho da estrutura para
    //definir o tamanho maximo da estrutura, limitando a utilização dos recursos da máquina
    //variaveis de intancia
    var ponteiroTopo = NoDuplo? = null
    var quantidade = 0

    //metodos auxiliares
    override fun estaVazia(): Boolean {
        return quantidade == 0
    }

    override fun estaCheia(): Boolean {
        return quantidade == tamanho
    }

    //verificar sempre se a implementação de metodos de adicionar e remover funciona bem em todos os casos, por exemplo quando estiver vazia
    override fun empilhar(dado: Any?){

        if(!estaCheia()){
            var novoNo = NoDuplo(dado)
            novoNo.anterior = ponteiroTopo?
            if(!estaVazia())
                ponteiroTopo?.proximo = novoNo
            ponteiroTopo? = novoNo
            quantidade = quantidade.inc()
        }else {
            println("Pilha Cheia!")
        }
    }
    //continua parecido com o espiar, assim como nas pilhas estaticas
    override fun desempilhar(): Any?{
        var dadoTopo: Any? = null //var pode ter o valor alterado, val é fixo
        if (!estaVazia()){
            dadoTopo = ponteiroTopo?.dado
            ponteiroTopo? = ponteiroTopo?.anterior
            if(ponteiroTopo!= null) //se tiver só um elemento, eu não vou nulificar o proximo elemento do ponteiroTopo
                ponteiroTopo.proximo = null
            quantidade = quantidade.dec() //tanto faz usar o -- ou qtd.dec(), o kotlin só recomenda usar inc ou dec
        }else {
            println("Esta vazia!")
        }
        return dadoTopo

    }

    override fun espiar(): Any?{
        var dadoTopo: Any? = null
        if(!estaVazia()){
            dadoTopo = ponteiroTopo?.dado
        } else {
            println("Pilha Vazia")
        }
        return dadoTopo
    }

    override fun atualizar(dado: Any?){
        if(!estaVazia())
            ponteiroTopo?.dado = dado
        else
            println("Pilha Vazia")
    }

    override fun imprimir(): String {
        var ponteiroAuxiliar? = ponteiroTopo? // faço isso pra não mexer na variavel ponteiroTopo, aí posso mexer na var ponteiroAuxiliar
        // já que eu vou decrementando o ponteiroAuxiliar até a última posição, para não consumir
        //  minha estrutura durante a repetição
        var resultado = "["
        for (i in 0 until quantidade){
            if(i != quantidade-1)
                resultado+= ","
            ponteiroAuxiliar? = ponteiroAuxiliar?.anterior
        }
        return "$resultado]"
    }