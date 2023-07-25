package com.utsman.resources

import com.utsman.entity.Banner
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
class ProductResources {

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
    suspend fun getProductById(@PathParam("id") id: Int): BaseResponse<Product> {
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
    suspend fun featuredProduct(
        @QueryParam("page") page: Int,
        @QueryParam("per_page") pageSize: Int
    ): BaseResponse<Paged<Product>> {
        val validatePage = if (page == 0) 1 else page
        val validatePageSize = if (pageSize == 0) 10 else pageSize

        val product = productService.getFeaturedProduct(validatePage, validatePageSize)
        return product.toResponse()
    }

    @GET
    @Path("/category/{category_id}")
    suspend fun productByCategory(
        @PathParam("category_id") categoryId: Int,
        @QueryParam("page") page: Int,
        @QueryParam("per_page") pageSize: Int
    ): BaseResponse<Paged<Product>> {
        if (categoryId == 0) throw BadRequestException("'category_id' invalid!")

        val validatePage = if (page == 0) 1 else page
        val validatePageSize = if (pageSize == 0) 10 else pageSize

        val product = productService.getProductByCategory(categoryId, validatePage, validatePageSize)
        return product.toResponse()
    }

    @GET
    @Path("/brand/{brand_id}")
    suspend fun productByBrand(
        @PathParam("brand_id") brandId: Int,
        @QueryParam("page") page: Int,
        @QueryParam("per_page") pageSize: Int
    ): BaseResponse<Paged<Product>> {
        if (brandId == 0) throw BadRequestException("'brand_id' invalid!")

        val validatePage = if (page == 0) 1 else page
        val validatePageSize = if (pageSize == 0) 10 else pageSize

        val product = productService.getProductByBrand(brandId, validatePage, validatePageSize)
        return product.toResponse()
    }

    @GET
    @Path("/banner")
    suspend fun getBanner(): BaseResponse<List<Banner>> {
        return productService.getBanner().toResponse()
    }
}