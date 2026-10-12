package com.example.task_2_10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task_2_10.ui.theme.Task_2_10Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ShoppingCartScreen()
        }
    }

    @Composable
    fun ShoppingCartScreen() {
        var products by remember {
            mutableStateOf(
                listOf(
                    Product(0, "Товар #1", 100),
                    Product(1, "Товар #2", 150),
                    Product(2, "Товар #3", 56)
                )
            )
        }
        val totalSum = products.sumOf { it.price }
        val productSize = products.size
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            for (product in products) {
                Text(text = product.name + " - " + product.price + " рублей")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Товаров на сумму: $totalSum рублей")
            AddProductSection(
                totalSum = totalSum,
                onAdd = {
                    val product = Product(
                        id = productSize,
                        name = "Товар #${productSize + 1}",
                        price = Random.nextInt(0, 100)
                    )
                    products += product
                })
            RemoveProductSection(
                productSize = productSize,
                onRemove = {
                val product = products.last()
                products = products.dropLast(1)
            })
        }
    }

    @Composable
    fun AddProductSection(
        onAdd: () -> Unit,
        totalSum: Int
    ) {
        // Если сумма заказа больше 500, то показываем сообщение о бесплатной доставке
        if (totalSum > 500) {
            Toast.makeText(LocalContext.current, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
        }
        Button(onClick = onAdd) {
            Text(text = "Добавить товар")
        }
    }

    @Composable
    fun RemoveProductSection(
        onRemove: () -> Unit,
        productSize: Int
    ) {
        if (productSize > 0) {
            Button(onClick = onRemove) {
                Text(text = "Удалить товар")
            }
        }
    }
}
