package com.utsman.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Banner(
    val id: Int,
    @JsonProperty("product_id")
    val productId: Int,
    val productImage: String,
    val description: String,
    @JsonProperty("color_primary")
    val colorPrimary: String,
    @JsonProperty("color_accent")
    val colorAccent: String
)