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
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n" +
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")

        while (true) {
            println("<주문 메뉴>")
            val input = Console.readLine() ?: ""
            if (input.equals("end", ignoreCase = true)) {
                break
            }

            try {
                val (menu, quantity) = input.split("-")
                if (quantity.toIntOrNull() ?: 0 > 0) {
                    orderItems[menu] = quantity.toInt()
                } else {
                    println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
                }
            } catch (e: Exception) {
                println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }
        }
        return orderItems
    }
//    fun showEventDetails(result: EventResult) {
//        // 이벤트 결과 출력
//    }
    fun showError(errorMessage: String?) {
        println("[ERROR] $errorMessage")
    }
}
