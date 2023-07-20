package com.utsman.controller

import com.utsman.entity.response.BaseResponse
import com.utsman.utils.toResponse
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/ping")
class PingController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun ping(): BaseResponse<String> {
        return "pong".toResponse()
    }
}