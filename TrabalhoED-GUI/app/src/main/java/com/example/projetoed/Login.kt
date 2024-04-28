package src

class Login {

    fun autenticacao(user : String?, senha : String?) : Boolean{
        val listaAdministradores : Array<Usuario> = ListaAdministradores().getAdministradores()
        if(user != null || senha != null){
            for (i in listaAdministradores){
                if(i.getUser() == user && i.getSenha() == senha){
                    println("Bem-vindo administrador!")
                    return true
                }
            }
        }
        println("Usuário ou senha inválidos")
        return false
    }
}