package com.utsman.resources

import com.utsman.entity.Brand
import com.utsman.entity.response.BaseResponse
import com.utsman.service.BrandService
import com.utsman.utils.toResponse
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/brand")
class BrandResources {

    @Inject
    private lateinit var brandService: BrandService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun brand(): BaseResponse<List<Brand>> {
        val brands = brandService.getBrand()
        return brands.toResponse()
    }
}