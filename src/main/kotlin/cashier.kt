var list: MutableList<Bill> = mutableListOf(Bill.Cien(), Bill.Mil(),
    Bill.Veinte(), Bill.Cincuenta(), Bill.Veinte(), Bill.Veinte(),
    Bill.Veinte(), Bill.Veinte())
var billetes:List<Int> = listOf(20, 50, 100, 200, 500, 1000)
var veinte:MutableList<Bill> = mutableListOf()
var cincuenta:MutableList<Bill> = mutableListOf()
var cien:MutableList<Bill> = mutableListOf()
var doscientos:MutableList<Bill> = mutableListOf()
var quinientos:MutableList<Bill> = mutableListOf()
var mil:MutableList<Bill> = mutableListOf()
open class Cashier(var bills: MutableList<Bill> = list) {
    var money:Int = 0
    constructor() : this(list) {
        for(e in billetes){
            var filter = bills.filter { it.value == e }
            if(filter.isNotEmpty()){
                this.money += e
            }
        }
    }
    fun refresh(){
        veinte = bills.filter { it.value == 20 } as MutableList<Bill>
        cincuenta = bills.filter { it.value == 50 } as MutableList<Bill>
        cien = bills.filter { it.value == 100 } as MutableList<Bill>
        doscientos = bills.filter { it.value == 200 } as MutableList<Bill>
        quinientos = bills.filter { it.value == 500 } as MutableList<Bill>
        mil = bills.filter { it.value == 1000 } as MutableList<Bill>
    }
    fun insert(quantity:Int, bills:String){
        var x:Bill = Bill.Cien()
        when(bills) {
            "20" -> x = Bill.Veinte()
            "50" -> x = Bill.Cincuenta()
            "100" -> x = Bill.Cien()
            "200" -> x = Bill.Doscientos()
            "500" -> x = Bill.Quinientos()
            "1000" -> x = Bill.Mil()
        }
        for (e in 1..quantity){
            this.bills.add(x)
        }
    }
    fun status(){
        updateBills()
        println("Dinero en el cajero: ${this.money}")
        println("Cantidad de  billetes: ${bills.size}")
        for(e in billetes){
            var filter = bills.filter { it.value == e }
            if(filter.isNotEmpty()){
                println("Cantidad de billetes de $e pesos: ${filter.size}")
            }
        }
    }
    fun retire(amount:Float, user:UserData){
        if(amount < 20){
            println("Lo minimo para retirar son 20 pesos")
        }else{
            if(user.money >= amount){
                val value20 = amount / 20F
                val value50 = amount / 50F
                val value10 = amount / 10F
                if(value10 % 1 != 0.0F){
                    println("El monto a ingresar debe de ser divisible de 10")
                }else{
                    if(this.money >= amount){
                        var billetes:MutableList<Bill> = mutableListOf()
                        var realAmount = amount
                        refresh()
                        while(realAmount > 0){
                            println(realAmount)
                            when(true){
                                realAmount < 100F &&  value20 % 1 == 0.0F && veinte.isNotEmpty()->{
                                    billetes.add(Bill.Veinte())
                                    var veinte = bills.filter { it.value == 20 } as MutableList<Bill>
                                    bills.remove(veinte[0])
                                    realAmount -= 20
                                    refresh()
                                }
                                realAmount < 50F && realAmount >= 20F && veinte.isNotEmpty() -> {
                                    billetes.add(Bill.Veinte())
                                    realAmount -= 20
                                    var veinte = bills.filter { it.value == 20 } as MutableList<Bill>
                                    bills.remove(veinte[0])
                                    refresh()
                                }
                                realAmount < 100F && realAmount >= 50F &&  value20 % 1 != 0.0F && cincuenta.isNotEmpty() -> {
                                    billetes.add(Bill.Cincuenta())
                                    realAmount -= 50
                                    var cincuenta = bills.filter { it.value == 50 } as MutableList<Bill>
                                    bills.remove(cincuenta[0])
                                    refresh()
                                }
                                realAmount < 200F && realAmount >= 100F && cien.isNotEmpty() -> {
                                    billetes.add(Bill.Cien())
                                    realAmount -= 100
                                    var cien = bills.filter { it.value == 100 } as MutableList<Bill>
                                    bills.remove(cien[0])
                                    refresh()
                                }
                                realAmount < 300F && realAmount >= 200F && doscientos.isNotEmpty() -> {
                                    billetes.add(Bill.Doscientos())
                                    realAmount -= 200
                                    var doscientos = bills.filter { it.value == 200 } as MutableList<Bill>
                                    bills.remove(doscientos[0])
                                }
                                realAmount < 1000F && realAmount >= 500F && quinientos.isNotEmpty() -> {
                                    billetes.add(Bill.Quinientos())
                                    realAmount -= 500
                                    var quinientos = bills.filter { it.value == 500 } as MutableList<Bill>
                                    bills.remove(quinientos[0])
                                }
                                realAmount >= 1000F && mil.isNotEmpty() -> {
                                    billetes.add(Bill.Mil())
                                    realAmount -= 1000
                                    var mil = bills.filter { it.value == 1000 } as MutableList<Bill>
                                    bills.remove(mil[0])
                                }
                                else -> {
                                    realAmount = 0F
                                    println("No fue posible hacer el retiro")
                                }
                            }
                            println("monto real $realAmount")
                        }
                        User.retire(amount, user.userName, user.pin)
                        for(e in billetes){
                            println(e.value)
                        }
                        val veinte = bills.filter { it.value == 20 }
                        val cincuenta = bills.filter { it.value == 50 }
                        val cien = bills.filter { it.value == 100 }
                        val doscientos = bills.filter { it.value == 200 }
                        val quinientos = bills.filter { it.value == 500 }
                        val mil = bills.filter { it.value == 1000 }
                        println("Cantidad de billetes de 20: ${veinte.size}")
                        println("Cantidad de billetes de 50: ${cincuenta.size}")
                        println("Cantidad de billetes de 100: ${cien.size}")
                        println("Cantidad de billetes de 200: ${doscientos.size}")
                        println("Cantidad de billetes de 500: ${quinientos.size}")
                        println("Cantidad de billetes de 1000: ${mil.size}")
                    }else{
                        println("El cajero no cuenta con el dinero suficiente")
                    }
                }
            }else{
                println("Fondos insuficientes")
            }
        }
    }
    private fun updateBills(){
        this.money = 0
        for(e in bills){
            this.money += e.value
        }
    }
}