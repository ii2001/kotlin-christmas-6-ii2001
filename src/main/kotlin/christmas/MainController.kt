package christmas

class MainController {
    private val plannerView = PlannerView()
    //private val plannerModel = PlannerModel()

    fun run() {
        plannerView.showWelcomeMessage()
        val visitDate = plannerView.getVisitDate()
        val orderItems = plannerView.getOrderItems()

        try {
            val result = plannerModel.calculateEventBenefits(visitDate, orderItems)
            plannerView.showEventDetails(result)
        } catch (e: Exception) {
            plannerView.showError(e.message)
        }
    }
}