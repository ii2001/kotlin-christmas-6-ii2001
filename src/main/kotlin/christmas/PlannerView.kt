package christmas

import camp.nextstep.edu.missionutils.Console

class PlannerView {
    fun getVisitDate(): Int {
        // 날짜 입력 받기
        while (true) {
            println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!) ")
            val visitDate=inputVisitDate()
            if (visitDate!=0){
                return visitDate
            }
        }
    }
    fun inputVisitDate(): Int{
        try {
            val visitDate = Console.readLine().toIntOrNull()
            if (visitDate != null && visitDate in 1..31) {
                return visitDate
            } else {
                println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
            }
        } catch (e: NumberFormatException) {
            println("[ERROR] 숫자를 입력해 주세요.")
        }
        return 0
    }
    fun totalItemsException(pair: List<String>,menuList:List<String>){
        val (menu, quantity) = pair
        if (pair.size != 2) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.") }
        if (!menuList.contains(menu)) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
        if (quantity.toIntOrNull() ?: 0 <= 0) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }
    fun onlyBeveragesException(orderItems:MutableMap<String,Int>){
        val isOnlyBeverages = orderItems.keys.all { menuName ->
            beverages.any { it.name == menuName }
        }
        if (isOnlyBeverages) {
            throw IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다.")
        }
    }
    fun over20MenuException(orderItems:MutableMap<String,Int>){
        if (orderItems.values.sum() > 20) {
            throw IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.")
        }
    }
    fun inputOrderItems(menuQuantityPairs:List<String>,orderItems: MutableMap<String,Int>): MutableMap<String, Int> {
        val menuList = (appetizers + mainDishes + desserts + beverages).map { it.name }
        val totalItems = menuQuantityPairs.map {
            val pair = it.split("-")
            val (menu, quantity) = pair
            totalItemsException(pair,menuList)
            orderItems[menu] = quantity.toInt()
        }
        return orderItems
    }
    fun getOrderItems(): Map<String, Int> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1)")
        while (true) {
            var orderItems = mutableMapOf<String, Int>()
            val menuQuantityPairs = Console.readLine().trim().split(",")
            try {
                orderItems=inputOrderItems(menuQuantityPairs,orderItems)
                onlyBeveragesException(orderItems)
                over20MenuException(orderItems)
                return orderItems
            } catch (e: Exception) {
                println(e.message)
                continue // 예외가 발생하면 반복문을 계속 실행
            } }
        return emptyMap() }

    fun showError(errorMessage: String?) {
        println("[ERROR] $errorMessage")
    }
}