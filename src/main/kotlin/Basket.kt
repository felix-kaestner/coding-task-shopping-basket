import java.math.RoundingMode

/**
 * The Shopping Basket of a user.
 *
 * @property items The products in the basket.
 * @property deals The deals that are currently active.
 * @property total The total price of the basket including deals.
 */
class Basket(private val inventory: Inventory) {
    private val items = mutableListOf<Product>()
    private val deals = mutableListOf<Deal>()

    /**
     * The computed total price of the basket.
     */
    val total: Double
        get() =
            items.sumOf{ it.price } // Sum the prices of all items in the basket
                .minus(deals.sumOf { it.reduction(items) }) // Subtract the reduction of all deals from the total
                .toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble() // Round to 2 decimal places

    /**
     * Adds a product to the basket.
     *
     * @throws IllegalArgumentException if the product is not in the inventory.
     */
    fun scan(productName: String) {
        val product = inventory.getProductByName(productName) ?: throw IllegalArgumentException("Product not found")
        items.add(product)
    }

    /**
     * Adds a deal to the basket.
     *
     * Only adds a deal if it is not already in the basket.
     */
    fun addDeal(deal: Deal) {
        if (deal !in deals) {
            deals.add(deal)
        }
    }
}
