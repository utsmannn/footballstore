package com.utsman.entity.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Paged<T>(
    val page: Int,
    @JsonProperty("per_page")
    val perPage: Int,
    @JsonProperty("has_next_page")
    val hasNextPage: Boolean,
    val data: List<T>
)