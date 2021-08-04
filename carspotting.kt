data class Car(val spot: Int = 0, val plate: String = "", val color: String = "")

fun main() {
    var lotCreated = false
    var parkingSpots = Array<Car?>(4){null}

    // looping menu
    do{
        var input = readLine()!!
        var lst = input.split(" ")
        // TODO figure out why it is askin to be intialized 
        if (lotCreated == false && lst[0] != "create") {
            if(lst[0] == "exit") break
            println("Sorry, a parking lot has not been created.")
            continue
        }
        try{
            when (lst[0]) {
                "exit" -> break
                "create" -> {
                    parkingSpots = Array<Car?>(lst[1].toInt()){null}
                    lotCreated = true
                    println("Created a parking lot with ${lst[1]} spots.")
                }
                "park" -> parkingSpots[parkingSpots.indexOf(null)] = park(lst, parkingSpots)
                "leave" -> parkingSpots[lst[1].toInt() - 1] = leave(lst, parkingSpots)
                "status" -> status(parkingSpots) 
                "reg_by_color" -> reg_by_color(lst[1], parkingSpots)
                "spot_by_color" -> spot_by_color(lst[1], parkingSpots)
                "spot_by_reg" -> println(spot_by_reg(lst[1], parkingSpots))
            }
        } catch (e: Exception) {}
    } while(true) 
}

// parks cars in first avaible location
fun park(ls: List<String>, lot: Array<Car?>): Car? {
    if (lot.contains(null)) {
        println( "${ls[2]} car parked in spot ${lot.indexOf(null) + 1}.")
        return Car(lot.indexOf(null) + 1, ls[1], ls[2])
    } else {
        println("Sorry, the parking lot is full.")
        return null
    }
}
// car leaves from desinated location
fun leave(ls: List<String>, lot: Array<Car?>): Car? {
    if (lot[ls[1].toInt() - 1] == null) {
        println("There is no car in spot ${ls[1]}.")
    } else {
        println("Spot ${ls[1]} is free.")
    }
    return null
}
// checks what cars are currently parked
fun status(lot: Array<Car?>) {
    if (lot.all {it == null}) {
        println("Parking lot is empty.")
    } else {
        for (car in lot) {
            if (car != null) {
                println("${car.spot} ${car.plate} ${car.color}")
            }
        }
    }
}

// prints the regisration numbers of all cars of a select color
fun reg_by_color(clr: String, lot: Array<Car?>) {
    // TODO: function should prints the registration number of cars of a color (not case sensitive) (reg num sperated by ",")
    var arr = emptyArray<String>()
    for (car in lot) {
        if (car?.color?.toLowerCase() == clr.toLowerCase()) {
            arr += car?.plate
        }
    }
    if (arr.size == 0){
        println("No cars with color $clr were found.")
    } else {
        println(arr.joinToString())
    }
    
}

// prints parking spots of all cars of a select color
fun spot_by_color(clr: String, lot: Array<Car?>) {
    // TODO: function should prints parking spot # of all cars of a specific color (not case sensitive)
    var arr = emptyArray<Int>()
    for (i in 0 until lot.size) {
        if (lot[i]?.color?.toLowerCase() == clr.toLowerCase()) {
            arr += i + 1
        }
    }
    if (arr.size == 0){
        println("No cars with color $clr were found.")
    } else {
        println(arr.joinToString())
    }
}

// returns parking spot of a car with select registration number
fun spot_by_reg(reg: String, lot: Array<Car?>): String {
    // TODO: function should return number of the spot of a car based on its registration number
    for (i in 0 until lot.size) {
        if (lot[i]?.plate == reg) {
            return "${i + 1}"
        }
    }
    return "No cars with registration number $reg were found."
}
