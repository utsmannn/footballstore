package com.utsman.entity.response

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.annotation.Nonnull

open class BaseResponse<T>(@JsonIgnore @Nonnull val _data: T) {
    var status: Boolean = false
    var message: String = "Failure"
    val data = _data
}