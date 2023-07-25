package com.utsman.service

import com.utsman.entity.Banner
import com.utsman.entity.Brand
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

    suspend fun getProductById(id: Int): Product {
        return getProducts().find { it.id == id } ?: throw ProductException.NotFound()
    }

    suspend fun getProductByCategory(categoryId: Int, page: Int, pageSize: Int): Paged<Product> {
        val productResult = getProducts().filter { it.category.id == categoryId }
        return productResult.generatePaged(page, pageSize)
    }

    suspend fun getProductByBrand(brandId: Int, page: Int, pageSize: Int): Paged<Product> {
        val productResult = getProducts().filter { it.brand.id == brandId }
        return productResult.generatePaged(page, pageSize)
    }

    suspend fun searchProductByName(name: String, page: Int, pageSize: Int): Paged<Product> {
        val productsResult = getProducts().filter { it.name.lowercase().contains(name.lowercase()) }
        if (productsResult.isEmpty()) throw ProductException.NotFound()

        return productsResult.generatePaged(page, pageSize)
    }

    suspend fun getFeaturedProduct(page: Int, pageSize: Int): Paged<Product> {
        val product = getProducts().filter { it.isPromoted }
        return product.generatePaged(page, pageSize)
    }

    suspend fun getBanner(): List<Banner> {
        return productRepository.readBannerCsv()
    }
}