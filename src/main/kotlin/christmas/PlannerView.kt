package christmas

import camp.nextstep.edu.missionutils.Console

class PlannerView {
    fun showWelcomeMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun getVisitDate(): Int {
        // 날짜 입력 받기
        while (true) {
            println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!) ")
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
        }
    }

    fun getOrderItems(): Map<String, Int> {
        // 주문 메뉴 및 수량 입력 받기
        val orderItems = mutableMapOf<String, Int>()
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")

        while (true) {
            val input = Console.readLine().split(",")
            try {
                input.forEach { menuItem ->
                    val (menu, quantity) = menuItem.split("-")
                    if (quantity.toIntOrNull() ?: 0 > 0) {
                        orderItems[menu] = quantity.toInt()
                    } else {
                        throw IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                continue // 예외가 발생하면 반복문을 계속 실행
            }
            return orderItems
        }
    }
    fun showEventDetails(result: EventResult) {
        // 이벤트 결과 출력
        println("12월 ${result.visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")

        println("<주문 메뉴>")
        result.orderMenu.forEach { println("${it.key} ${it.value}개") }

        println("\n<할인 전 총주문 금액>")
        println("${result.totalBeforeDiscount}원")

        println("\n<증정 메뉴>")
        if (result.giftMenu.isNotEmpty()){
            println(result.giftMenu)
        } else{
            println("없음")
        }

        println("\n<혜택 내역>")
        if (result.benefits.isNotEmpty()) {
            result.benefits.forEach { println("$it: -${it}원") }
        } else {
            println("없음")
        }

        println("\n<총혜택 금액>")
        println("${result.totalBenefits}원")

        println("\n<할인 후 예상 결제 금액>")
        println("${result.totalAfterDiscount}원")

        println("\n<12월 이벤트 배지>")
        println(result.eventBadge)
    }
    fun showError(errorMessage: String?) {
        println("[ERROR] $errorMessage")
    }
}
