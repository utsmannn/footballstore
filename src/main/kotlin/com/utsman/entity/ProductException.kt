package com.utsman.entity

import jakarta.ws.rs.ClientErrorException
import jakarta.ws.rs.core.Response

class ProductException(private val status: Response.Status, private val _message: String?) : ClientErrorException(_message, status) {

    companion object {
        @Suppress("FunctionName")
        fun NotFound(): ProductException {
            return ProductException(
                status = Response.Status.NOT_FOUND,
                _message = "Product not found"
            )
        }
    }
}