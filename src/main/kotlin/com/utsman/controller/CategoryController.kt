package com.utsman.controller

import com.utsman.entity.response.BaseResponse
import com.utsman.entity.Category
import com.utsman.service.CategoryService
import com.utsman.utils.toResponse
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/category")
class CategoryController {

    @Inject
    private lateinit var categoryService: CategoryService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun categories(): BaseResponse<List<Category>> {
        val categories = categoryService.getCategories()
        return categories.toResponse()
    }
}