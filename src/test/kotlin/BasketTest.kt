import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BasketTest {
    private val productA1 = Product("A0001", 12.99)
    private val productA2 = Product("A0002", 3.99)
    private val inventory = Inventory(listOf(productA1, productA2))

    @Test
    fun scan() {
        val basket = Basket(inventory)
        basket.scan("A0001")
        assertEquals(12.99, basket.total)
    }

    @Test
    fun deals() {
        // Buy1Get1Free A0002
        var basket = Basket(inventory)
        basket.scan("A0002")
        basket.scan("A0001")
        basket.scan("A0002")
        basket.addDeal(Deal.Buy1Get1Free(productA2))
        assertEquals(16.98, basket.total)

        // 10Percent A0001
        basket = Basket(inventory)
        basket.scan("A0002")
        basket.scan("A0001")
        basket.scan("A0002")
        basket.addDeal(Deal.Discount(10.0, productA1))
        assertEquals(19.67, basket.total)
    }
}
