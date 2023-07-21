package com.utsman.entity

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val image: List<String>,
    val isPromoted: Boolean
)