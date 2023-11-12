package christmas

data class Menu(val name: String, val price: Int)

val appetizers = listOf(
    Menu("양송이수프", 6000),
    Menu("타파스", 5500),
    Menu("시저샐러드", 8000)
)

val mainDishes = listOf(
    Menu("티본스테이크", 55000),
    Menu("바비큐립", 54000),
    Menu("해산물파스타", 35000),
    Menu("크리스마스파스타", 25000)
)

val desserts = listOf(
    Menu("초코케이크", 15000),
    Menu("아이스크림", 5000)
)

val beverages = listOf(
    Menu("제로콜라", 3000),
    Menu("레드와인", 60000),
    Menu("샴페인", 25000)
)