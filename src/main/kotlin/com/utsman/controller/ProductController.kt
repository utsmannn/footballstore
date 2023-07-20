package com.utsman.controller

import com.utsman.entity.response.BaseResponse
import com.utsman.entity.Product
import com.utsman.entity.response.Paged
import com.utsman.service.ProductService
import com.utsman.utils.toResponse
import jakarta.inject.Inject
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/product")
class ProductController {

    @Inject
    private lateinit var productService: ProductService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun products(
        @QueryParam("page") page: Int,
        @QueryParam("per_page") pageSize: Int
    ): BaseResponse<Paged<Product>> {

        // need validate here because quarkus param not support default value
        val validatePage = if (page == 0) 1 else page
        val validatePageSize = if (pageSize == 0) 10 else pageSize

        val products = productService.getProductByPage(validatePage, validatePageSize)
        return products.toResponse()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getProductById(@PathParam("id") id: String): BaseResponse<Product> {
        val product = productService.getProductById(id)
        return product.toResponse()
    }

    @GET
    @Path("/search")
    suspend fun searchProductByName(
        @QueryParam("q") name: String,
        @QueryParam("page") page: Int,
        @QueryParam("per_page") pageSize: Int
    ): BaseResponse<Paged<Product>> {

        // need validate here because quarkus param not support default value
        val validatePage = if (page == 0) 1 else page
        val validatePageSize = if (pageSize == 0) 10 else pageSize

        val product = productService.searchProductByName(name, validatePage, validatePageSize)
        return product.toResponse()
    }

    @GET
    @Path("/featured")
    suspend fun featuredProduct(): BaseResponse<List<Product>> {
        val product = productService.getFeaturedProduct()
        return product.toResponse()
    }

    @GET
    @Path("/category/{category_id}")
    suspend fun productByCategory(
        @PathParam("category_id") categoryId: String,
        @QueryParam("page") page: Int,
        @QueryParam("per_page") pageSize: Int
    ): BaseResponse<Paged<Product>> {
        if (categoryId.isEmpty()) throw BadRequestException("'category_id' invalid!")

        val validatePage = if (page == 0) 1 else page
        val validatePageSize = if (pageSize == 0) 10 else pageSize

        val product = productService.getProductByCategory(categoryId, validatePage, validatePageSize)
        return product.toResponse()
    }
}