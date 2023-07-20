package com.utsman.service

import com.utsman.entity.Category
import com.utsman.repository.CategoryRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class CategoryService {

    @Inject
    private lateinit var categoryRepository: CategoryRepository

    suspend fun getCategories(): List<Category> {
        return categoryRepository.readCategoriesCsv()
    }
}