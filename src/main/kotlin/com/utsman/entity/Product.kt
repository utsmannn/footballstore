package com.utsman.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val category: MiniCategory,
    val price: Double,
    val image: List<String>,
    @JsonProperty("is_promoted")
    val isPromoted: Boolean,
    val brand: MiniBrand
) {
    data class MiniCategory(
        val id: Int,
        val name: String
    )

    data class MiniBrand(
        val id: Int,
        val name: String
    )
}