package christmas

class PlannerModel {
    fun calculateEventBenefits(visitDate: Int, orderItems: Map<String, Int>): EventResult {
        val totalBeforeDiscount = calculateTotalOrderAmount(orderItems)
                val giftMenu = calculateGiftMenu(totalBeforeDiscount)
        val benefits = calculateBenefits(visitDate, orderItems)
        if (giftMenu!="없음"){ benefits["증정 이벤트"]=25000 }
        val totalBenefits = calculateDiscountAmount(benefits)
        val totalAfterDiscount = totalBeforeDiscount - totalBenefits
        val eventBadge = calculateEventBadge(totalBeforeDiscount)
        val weekend= calculateWeekend(visitDate)
        if (totalBeforeDiscount<10000){
            return EventResult(visitDate = visitDate, orderMenu = orderItems, totalBeforeDiscount = totalBeforeDiscount, giftMenu = "없음", benefits = mapOf(), totalBenefits = 0, totalAfterDiscount = totalBeforeDiscount, eventBadge = "없음", weekend="없음") }
        return EventResult(visitDate = visitDate, orderMenu = orderItems, totalBeforeDiscount = totalBeforeDiscount, giftMenu = giftMenu, benefits = benefits, totalBenefits = totalBenefits, totalAfterDiscount = totalAfterDiscount, eventBadge = eventBadge, weekend=weekend)
    }
    private fun isWeekend(visitDate: Int): Boolean {
        return visitDate % 7 == 1 || visitDate % 7 == 2
    }
    private fun calculateWeekend(visitDate: Int): String {
        val temp=isWeekend(visitDate) //true(주말),false(평일)여부
        if (temp){
            return "주말 할인"
        } else{
            return "평일 할인"
        }
    }
    private fun calculateTotalOrderAmount(orderItems: Map<String, Int>): Int {
        return orderItems.entries.sumBy { entry ->
            val menu = findMenu(entry.key)
            menu?.price?.times(entry.value) ?: 0
        }
    }
    private fun findMenu(menuName: String): Menu? {
        val allMenus = listOf(appetizers, mainDishes, desserts, beverages).flatten()
        return allMenus.find { it.name == menuName }
    }
    private fun calculateDiscountAmount(benefits: Map<String,Int>): Int {
        return benefits.values.sumBy{it}
    }
    private fun christmasCalculateDiscountAmount(visitDate: Int): Int {
        if (visitDate>25){
            return 0
        }
        return 900+100*(visitDate)
    }
    private fun calculateGiftMenu(totalBeforeDiscount: Int): String {
        return if (totalBeforeDiscount >= 120000) "샴페인 1개" else "없음"
    }
    private fun calculateMainDishDiscount(orderItems: Map<String, Int>, mainDishes: List<Menu>): Int {
        val mainDishCount = orderItems.filter { entry ->
            mainDishes.any { it.name == entry.key }
        }.values.sum()

        return mainDishCount * 2023
    }
    private fun calculateDessertDiscount(orderItems: Map<String, Int>, desserts: List<Menu>): Int {
        val dessertsCount = orderItems.filter { entry ->
            desserts.any { it.name == entry.key }
        }.values.sum()
        return dessertsCount * 2023
    }
    private fun weekendDiscount(visitDate: Int, orderItems: Map<String, Int>, mainDishes: List<Menu>, desserts: List<Menu>): Int {
        return if (isWeekend(visitDate)) {
            calculateMainDishDiscount(orderItems, mainDishes)
        } else {
            calculateDessertDiscount(orderItems, desserts)
        }
    }

    private fun calculateBenefits(visitDate: Int, orderItems: Map<String, Int>): MutableMap<String, Int> {
        val result = mutableMapOf<String, Int>()
        if (visitDate<=25){
            result["크리스마스 디데이 할인"]=christmasCalculateDiscountAmount(visitDate)
        }
        val weekendDiscountAmount = weekendDiscount(visitDate, orderItems, mainDishes, desserts)
        if (weekendDiscountAmount!=0){
            result[calculateWeekend(visitDate)] = weekendDiscountAmount
        }
        if (visitDate%7==3||visitDate==25){
            result["특별 할인"]=1000
        }
        return result
    }

    private fun calculateEventBadge(totalBenefits: Int): String {
        return when {
            totalBenefits >= 20000 -> "산타"
            totalBenefits >= 10000 -> "트리"
            totalBenefits >= 5000 -> "별"
            else -> "없음"
        }
    }
}