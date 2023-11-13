package christmas

class MainController {
    private val plannerView = PlannerView()
    private val plannerModel = PlannerModel()
    private val OutputView = OutputView()
    fun run() {
        OutputView.showWelcomeMessage()
        val visitDate = plannerView.getVisitDate()
        val orderItems = plannerView.getOrderItems()
        try {
            val result = plannerModel.calculateEventBenefits(visitDate, orderItems)
            OutputView.showEventDetails(result)
        } catch (e: Exception) {
            plannerView.showError(e.message) } } }