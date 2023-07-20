package com.utsman.config

import com.utsman.entity.response.BaseResponse
import com.utsman.entity.ProductException
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider


@Provider
class GlobalExceptionMapper : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception?): Response {
        val status = when (exception) {
            is NotFoundException -> Response.Status.NOT_FOUND
            is BadRequestException -> Response.Status.BAD_REQUEST
            is ProductException -> Response.Status.NOT_FOUND
            else -> Response.Status.INTERNAL_SERVER_ERROR
        }
        return Response.status(status)
            .entity(BaseResponse(null).also { it.message = exception?.message.orEmpty() })
            .build()
    }

}