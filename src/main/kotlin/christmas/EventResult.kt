package christmas

data class EventResult(
    val orderMenu: List<String>,
    val totalBeforeDiscount: Int,
    val giftMenu: String,
    val totalBenefits: Int,
    val totalAfterDiscount: Int,
    val eventBadge: String,
    val visitDate: Int,
    val benefits: List<String>
)
