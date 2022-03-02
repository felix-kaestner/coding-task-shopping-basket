/**
 * The inventory consist of a list of products.
 *
 * It is assumed that the products are of infinite quantity, e.g. a EBook.
 */
@JvmInline
value class Inventory(private val items: List<Product> = listOf()) {
    fun getProductByName(productName: String): Product? = items.find { item -> item.name == productName }
}
