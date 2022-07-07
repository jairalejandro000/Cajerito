open class Bill(val value:Int) {
    data class Veinte(val veinte:Int = 20 ):Bill(veinte)
    data class Cincuenta(val cincuenta:Int = 50):Bill(cincuenta)
    data class Cien(val cien :Int = 100):Bill(cien)
    data class Doscientos(val doscientos:Int = 200):Bill(doscientos)
    data class Quinientos(val quinientos:Int = 500):Bill(quinientos)
    data class Mil(val mil:Int = 1000):Bill(mil)
}