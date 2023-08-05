package com.utsman.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val category: MiniCategory,
    val price: Double,
    val images: List<String>,
    @JsonProperty("is_promoted")
    val isPromoted: Boolean,
    val brand: MiniBrand
) {
    data class MiniProduct(
        val id: Int,
        val name: String,
        val price: Double,
        val category: MiniCategory,
        val isPromoted: Boolean,
        val brand: MiniBrand,
        val image: String
    )

    data class MiniCategory(
        val id: Int,
        val name: String
    )

    data class MiniBrand(
        val id: Int,
        val name: String,
        val logo: String
    )

    fun toMiniProduct(): MiniProduct {
        return MiniProduct(
            id = id,
            name = name,
            price = price,
            isPromoted = isPromoted,
            brand = brand,
            image = images.firstOrNull().orEmpty(),
            category = category
        )
    }
}