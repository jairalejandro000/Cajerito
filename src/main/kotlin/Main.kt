import kotlin.system.exitProcess
fun main() {
    fun insert(){
        println("Ingrese la cantidad de billetes....")
        var a:Int = readln().toInt()
        println("Ingrese el valor de los billetes....")
        var b:String = readln().toString()
        Cashier().insert(a, b)
    }
    fun bye(){
        exitProcess(1)
    }
    fun createUser(){
        println("Ingrese su nombre de usuario....")
        var a:String = readln().toString()
        println("Ingrese su pin..................")
        var b:Int = readln().toInt()
        println("Ingrese el total de su cuenta....")
        var c:Float = readln().toFloat()
        User.addUser(UserData(a, b, c))
    }
    fun getUsers(){
        println(User.getUsers().map { it })
    }
    fun checkCredentials(type: Int){
        println("Ingrese su usuario..............")
        var a:String = readln().toString()
        println("Ingrese su pin..................")
        var b:Int = readln().toInt()
        var user = User.getUser(a, b)
        if(type == 1){
            println("Cantidad a retirar...............")
            var c:Float = readln().toFloat()
            if(user.isEmpty()){
                println("Error en las credenciales")
            }else{
                for (x in user){
                    Cashier().retire(c, x.value)
                }
            }
        }else if(type == 2){
            println("Cantidad a ingresar...............")
            var c:Float = readln().toFloat()
            val value10 = c / 10
            if(value10 % 1 != 0.0F){
                println("El monto a ingresar debe de ser divisible de 10")
            }else{
                if(user.isEmpty()){
                    println("Error en las credenciales")
                }else{
                    var t = true
                    var amount = 0F
                    while (amount != c){
                        println("Ingrese la cantidad de billetes....")
                        var a:Int = readln().toInt()
                        println("Ingrese el valor de los billetes....")
                        var b:String = readln().toString()
                        val v = a * b.toInt()
                        Cashier().insert(a, b)
                        amount += v.toFloat()
                        if(amount > c){
                            println("Nomas por chistoso aquí te vas a quedar")
                        }
                    }
                    if(amount == c){
                        println("En proceso de depositar")
                        User.insert(amount, a, b)
                    }
                }
            }
        }
    }
    fun accountStatus(){
        println("Ingrese su usuario..............")
        var a:String = readln().toString()
        println("Ingrese su pin..................")
        var b:Int = readln().toInt()
        var user = User.getUser(a, b)
        for (e in user.values){
            println("Propietario.....................")
            println(e.userName)
            println("Pin.............................")
            println(e.pin)
            println("Saldo disponible.................")
            println(e.money)
        }
    }
    fun menu(){
        val lista:List<String> = listOf(
            "1.- Insertar dinero................",
            "2.- Retirar dinero.................",
            "3.- Crear cuenta...................",
            "4.- Depositar......................",
            "5.- Estado de cuenta...............",
            "6.- Dinero disponible..............",
            "7.- Salir..........................")
        for(e in lista){
            println(e)
        }
        var x: Int = readln().toInt()
        when (x){
            1 -> insert()
            2 -> checkCredentials(1)
            3 -> createUser()
            4 -> checkCredentials(2)
            5 -> accountStatus()
            6 -> Cashier().status()
            7 -> bye()
            8 -> getUsers()
            else -> println("Adios")
        }
        menu()
    }
    menu()
}