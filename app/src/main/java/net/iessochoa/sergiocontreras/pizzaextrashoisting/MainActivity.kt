package net.iessochoa.sergiocontreras.pizzaextrashoisting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.pizzaextrashoisting.ui.theme.PizzaExtrasHoistingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaExtrasHoistingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OrderScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun OrderScreen(modifier: Modifier = Modifier) {
    var order by remember { mutableStateOf(DummyData.randomOrder()) }
    // La pantalla es la dueña de las selecciones de todas las pizzas.
    // Clave: id de pizza. Valor: ids de extras seleccionados en esa pizza.
    val selectedExtras = remember { mutableStateMapOf<Int, Set<Int>>() }
    val context = LocalContext.current

    val orderTotal = order.pizzas.sumOf { pizza ->
        pizza.basePrice + DummyData.extras
            .filter { it.id in (selectedExtras[pizza.id] ?: emptySet()) }
            .sumOf { it.price }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(Modifier.height(12.dp))
            Text("Pizzería", style = MaterialTheme.typography.headlineMedium)
            Text("Pedido #${order.id}", style = MaterialTheme.typography.titleMedium)
        }
        items(order.pizzas, key = { it.id }) { pizza ->
            PizzaCard(
                pizza = pizza,
                extras = DummyData.extras,
                selectedExtraIds = selectedExtras[pizza.id] ?: emptySet(),
                onExtraSelectionChange = { extraId, selected ->
                    /* TODO */
                }
            )
        }
        item {
            Text(
                text = "Total pedido: ${orderTotal.euros()}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onClick = {
                    Toast.makeText(context, "¡Pedido pagado!", Toast.LENGTH_SHORT).show()
                    order = DummyData.randomOrder(excludingId = order.id)
                    selectedExtras.clear()
                }
            ) {
                Text("Pagar pedido")
            }
        }
    }
}

@Composable
private fun PizzaCard(
    pizza: Pizza,
    extras: List<Extra>,
    selectedExtraIds: Set<Int>,
    onExtraSelectionChange: (extraId: Int, selected: Boolean) -> Unit
) {
    val pizzaTotal = pizza.basePrice + extras
        .filter { it.id in selectedExtraIds }
        .sumOf { it.price }

    Card(Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(pizza.name, style = MaterialTheme.typography.titleMedium)
            Text("Precio base: ${pizza.basePrice.euros()}")
            Text("Extras", style = MaterialTheme.typography.labelLarge)
            extras.forEach { extra ->
                ExtraRow(
                    extra = extra,
                    selected = extra.id in selectedExtraIds,
                    onSelectionChange = { selected ->
                        /* TODO */
                    }
                )
            }
            Text(
                "Total pizza: ${pizzaTotal.euros()}",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ExtraRow(
    extra: Extra,
    selected: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = selected, onCheckedChange = onSelectionChange)
        Text(extra.name, modifier = Modifier.weight(1f))
        Text("+${extra.price.euros()}")
    }
}

private fun Double.euros(): String = "%.2f €".format(this)

@Preview(showBackground = true)
@Composable
private fun OrderScreenPreview() {
    PizzaExtrasHoistingTheme {
        OrderScreen()
    }
}
