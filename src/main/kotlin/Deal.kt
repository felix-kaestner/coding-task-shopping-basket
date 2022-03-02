import kotlin.math.floor

/**
 * A discount to be applied to a [Basket].
 * Discounts are applied using the GoF Visitor Pattern - https://en.wikipedia.org/wiki/Visitor_pattern.
 */
sealed class Deal(val name: String) {
    abstract fun reduction(products: List<Product>): Double

    data class Buy1Get1Free(private val product: Product) : Deal("Buy1Get1Free") {
        override fun reduction(products: List<Product>): Double {
            val quantity = products.count { product -> product == this.product }
            return floor((quantity / 2).toDouble()) * product.price
        }
    }

    data class Discount(private val percentage: Double, private val product: Product) : Deal("${percentage}Percent") {
        override fun reduction(products: List<Product>): Double {
            val quantity = products.count { product -> product == this.product }
            return quantity * product.price * percentage / 100
        }
    }
}
