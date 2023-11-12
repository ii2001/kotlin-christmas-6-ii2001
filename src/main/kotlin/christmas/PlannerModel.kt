package christmas

class PlannerModel {
    fun calculateEventBenefits(visitDate: Int, orderItems: Map<String, Int>): EventResult {
        val totalBeforeDiscount = calculateTotalOrderAmount(orderItems)
        val discountAmount = calculateDiscountAmount(visitDate, orderItems)
        val giftMenu = calculateGiftMenu(totalBeforeDiscount)
        val benefits = calculateBenefits(visitDate, orderItems)

        val totalBenefits = discountAmount.values.sum()
        val totalAfterDiscount = totalBeforeDiscount - totalBenefits
        val eventBadge = calculateEventBadge(totalBenefits)
        val weekend= calculateWeekend(visitDate)

        return EventResult(
            visitDate = visitDate,
            orderMenu = orderItems.keys.toList(),
            totalBeforeDiscount = totalBeforeDiscount,
            giftMenu = giftMenu,
            benefits = benefits,
            totalBenefits = totalBenefits,
            totalAfterDiscount = totalAfterDiscount,
            eventBadge = eventBadge,
            weekend=weekend
        )
    }

    private fun calculateWeekend(visitDate: Int): String {
        if (visitDate%7==1||visitDate%7==2){
            return "주말 할인"
        } else{
            return "평일 할인"
        }

    }

    private fun calculateTotalOrderAmount(orderItems: Map<String, Int>): Int {
        return orderItems.entries.sumBy { it.value }
    }

    private fun calculateDiscountAmount(visitDate: Int, orderItems: Map<String, Int>): Map<String, Int> {
        // 이벤트 별로 할인 계산 로직을 추가하세요.
        // 예시로 모든 메뉴에 대해 1,000원 할인을 적용한 것입니다.
        return orderItems.mapValues { (_, quantity) -> quantity * 1000 }
    }
    private fun christmasCalculateDiscountAmount(visitDate: Int,totalBeforeDiscount: Int): Int {
        if (visitDate>25){
            return totalBeforeDiscount
        }
        return totalBeforeDiscount-(900+100*(visitDate))
    }

    private fun calculateGiftMenu(totalBeforeDiscount: Int): String {
        return if (totalBeforeDiscount >= 120000) "샴페인" else "없음"
    }

    private fun calculateBenefits(visitDate: Int, orderItems: Map<String, Int>): List<String> {
        val result = mutableListOf<String>()
        if (visitDate<=25){
            result.add("크리스마스 디데이 할인")
        }
        result.add(calculateWeekend(visitDate))
        if (visitDate%7==3||visitDate==25){
            result.add("특별 할인")
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