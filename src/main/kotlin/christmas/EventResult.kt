package christmas

data class EventResult(
    val orderMenu: List<String>,
    val totalBeforeDiscount: Int,
    val giftMenu: String,
    val discountAmount: Int,
    val totalBenefits: Int,
    val totalAfterDiscount: Int,
    val eventBadge: String
)
