package src
data class Usuario(private var user : String,
              private var senha : String){

    fun getUser(): String {
        return user
    }

    fun setUser(user: String) {
        this.user = user
    }

    fun getSenha(): String {
        return senha
    }

    fun setSenha(senha: String) {
        this.senha = senha
    }
}