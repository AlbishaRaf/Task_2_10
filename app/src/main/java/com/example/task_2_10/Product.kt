package com.example.task_2_10

data class Cart(
    val products: List<Product>
)
data class Product(
    val id: Int,
    val name: String,
    val price: Int
)
