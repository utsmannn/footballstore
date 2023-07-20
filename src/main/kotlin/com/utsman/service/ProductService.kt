package com.utsman.service

import com.utsman.entity.Product
import com.utsman.entity.ProductException
import com.utsman.entity.response.Paged
import com.utsman.repository.ProductRepository
import com.utsman.utils.generatePaged
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.BadRequestException

@ApplicationScoped
class ProductService {

    @Inject
    private lateinit var productRepository: ProductRepository

    @Inject
    private lateinit var categoryService: CategoryService

    private suspend fun getProducts(): List<Product> {
        return productRepository.readProductsCsv()
    }

    suspend fun getProductByPage(page: Int, pageSize: Int): Paged<Product> {
        val products = getProducts()
        return products.generatePaged(page, pageSize)
    }

    suspend fun getProductById(id: String): Product {
        return getProducts().find { it.id == id } ?: throw ProductException.NotFound()
    }

    suspend fun getProductByCategory(categoryId: String, page: Int, pageSize: Int): Paged<Product> {
        val category = categoryService.getCategories().find { it.id == categoryId } ?: throw BadRequestException("'category_id' invalid!")
        val categoryName = category.name.lowercase()
        val productResult = getProducts().filter { it.category.lowercase() == categoryName }
        return productResult.generatePaged(page, pageSize)
    }

    suspend fun searchProductByName(name: String, page: Int, pageSize: Int): Paged<Product> {
        val productsResult = getProducts().filter { it.name.lowercase().contains(name.lowercase()) }
        if (productsResult.isEmpty()) throw ProductException.NotFound()

        return productsResult.generatePaged(page, pageSize)
    }

    suspend fun getFeaturedProduct(): List<Product> {
        val product = getProducts()
        return product.shuffled().take(5)
    }
}