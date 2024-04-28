package src

 class ListaAdministradores {
    private var administradores = arrayOf(Usuario("DedelDoCapa", "Dedel123"), Usuario("BonequinhaDoFF", "AinNobruApelao"), Usuario("1","2"))

    fun getAdministradores(): Array<Usuario> {
        return administradores
    }
}