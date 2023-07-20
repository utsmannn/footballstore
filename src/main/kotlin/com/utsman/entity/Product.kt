package com.utsman.entity

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val image: String
)