package net.iessochoa.sergiocontreras.pizzaextrashoisting

data class Extra(
    val id: Int,
    val name: String,
    val price: Double
)

data class Pizza(
    val id: Int,
    val name: String,
    val basePrice: Double
)

data class Order(
    val id: Int,
    val pizzas: List<Pizza>
)

/**
 * Catálogo y pedidos de ejemplo. Los datos son inmutables:
 * las selecciones del usuario pertenecen a OrderScreen, no a este singleton.
 */
object DummyData {
    val extras = listOf(
        Extra(id = 1, name = "Queso extra", price = 1.25),
        Extra(id = 2, name = "Champiñones", price = 0.90),
        Extra(id = 3, name = "Aceitunas", price = 0.75),
        Extra(id = 4, name = "Bacon", price = 1.50)
    )

    val orders = listOf(
        Order(
            id = 101,
            pizzas = listOf(
                Pizza(id = 1, name = "Margarita", basePrice = 8.50),
                Pizza(id = 2, name = "Cuatro quesos", basePrice = 10.25)
            )
        ),
        Order(
            id = 102,
            pizzas = listOf(
                Pizza(id = 3, name = "Barbacoa", basePrice = 11.00),
                Pizza(id = 4, name = "Vegetal", basePrice = 9.25),
                Pizza(id = 5, name = "Pepperoni", basePrice = 10.50)
            )
        ),
        Order(
            id = 103,
            pizzas = listOf(
                Pizza(id = 6, name = "Carbonara", basePrice = 10.75)
            )
        )
    )

    fun randomOrder(excludingId: Int? = null): Order {
        val candidates = orders.filter { it.id != excludingId }.ifEmpty { orders }
        return candidates.random()
    }
}
