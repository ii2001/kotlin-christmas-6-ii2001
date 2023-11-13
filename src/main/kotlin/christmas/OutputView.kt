package christmas

class OutputView {
    fun showWelcomeMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }
    fun showOrderMenu(result: EventResult){
        println("<주문 메뉴>")
        result.orderMenu.forEach { println("${it.key} ${it.value}개") }
    }
    fun showTotalBeforDiscount(result: EventResult){
        println("\n<할인 전 총주문 금액>")
        println("${result.totalBeforeDiscount}원")
    }
    fun showGiftMenu(result: EventResult){
        println("\n<증정 메뉴>")
        if (result.giftMenu.isNotEmpty()){
            println(result.giftMenu)
        } else{
            println("없음")
        }
    }
    fun showBenefits(result: EventResult){
        println("\n<혜택 내역>")
        if (result.benefits.isNotEmpty()) {
            result.benefits.forEach { println("${it.key}: -${it.value}원") }
        } else {
            println("없음")
        }
        println("\n<총혜택 금액>")
        println("${result.totalBenefits}원")
    }
    fun showTotalAfterDiscount(result: EventResult){
        println("\n<할인 후 예상 결제 금액>")
        if (result.giftMenu!="없음"){
            println("${result.totalAfterDiscount+25000}원")
        } else{
            println("${result.totalAfterDiscount}원")
        }
    }
    fun showEventBadge(result: EventResult){
        println("\n<12월 이벤트 배지>")
        println(result.eventBadge)
    }
    fun showEventDetails(result: EventResult) {
        println("12월 ${result.visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
        showOrderMenu(result)
        showTotalBeforDiscount(result)
        showGiftMenu(result)
        showBenefits(result)
        showTotalAfterDiscount(result)
        showEventBadge(result)
    }

    companion object
}