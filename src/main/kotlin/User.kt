data class UserData(
    var userName:String,
    var pin:Int,
    var money:Float
    )
object User{
    private var data: LinkedHashMap<String, UserData> = linkedMapOf("jair" to
            UserData("jair", 5841, 7800F))
    fun addUser(newUser:UserData){
        for (x in data.values){
            if(newUser.userName != x.userName){
                data[newUser.userName] = newUser
            }else{
                println("Error al crear la cuenta")
            }
        }
    }
    fun getUsers() = data.values.toList()
    fun getUser(userName: String, pin: Int): Map<String, UserData> {
        var filter: Map<String, UserData> = data.filter {
            it.value.userName == userName && it.value.pin == pin
        }
        return filter
    }

    fun retire(money: Float, userName: String, pin: Int){
        var user = getUser(userName, pin)
        println("Dinero a retirar de la cuenta: $money")
        for(e in user){
            e.value.money -= money
            println("Restante en la cuenta: " + e.value.money)
        }
    }

    fun insert(money: Float, userName: String, pin: Int){
        var user = getUser(userName, pin)
        println("Dinero a insertar de la cuenta: $money")
        for(e in user){
            e.value.money += money
            println("Disponible en la cuenta: " + e.value.money)
        }

    }
}