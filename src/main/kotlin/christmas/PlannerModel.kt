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
    }
    fun showError(errorMessage: String?) {
        // 에러 메시지 출력
    }
}
