package christmas

data class EventResult(
    val orderMenu: Map<String,Int>,
    val totalBeforeDiscount: Int,
    val giftMenu: String,
    val totalBenefits: Int,
    val totalAfterDiscount: Int,
    val eventBadge: String,
    val visitDate: Int,
    val benefits: Map<String,Int>,
    val weekend: String
)
