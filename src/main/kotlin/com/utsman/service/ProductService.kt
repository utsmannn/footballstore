package com.utsman.service

import com.utsman.entity.Banner
import com.utsman.entity.Product
import com.utsman.entity.ProductException
import com.utsman.entity.response.Paged
import com.utsman.repository.ProductRepository
import com.utsman.utils.generatePaged
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

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

    suspend fun getProductByIds(vararg id: Int): List<Product> {
        val products = getProducts()
        return id.map { i -> products.find { it.id == i } }.filterNotNull()
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

    suspend fun searchProductByName(name: String, page: Int, pageSize: Int, categoryId: Int, brandId: Int): Paged<Product> {
        val productsResult = getProducts().filter { it.name.lowercase().contains(name.lowercase()) }
            .run {
                if (categoryId != 0) {
                    filter { it.category.id == categoryId }
                } else {
                    this
                }
            }
            .run {
                if (brandId != 0) {
                    filter { it.brand.id == brandId }
                } else {
                    this
                }
            }
        if (productsResult.isEmpty()) throw ProductException.NotFound()

        return productsResult.generatePaged(page, pageSize)
    }

    suspend fun getFeaturedProduct(page: Int, pageSize: Int): Paged<Product> {
        val product = getProducts().filter { it.isPromoted }
        return product.generatePaged(page, pageSize)
    }

    suspend fun getTopProduct(): List<Product> {
        return getProducts().shuffled().take(10)
    }

    suspend fun getCuratedProduct(): List<Product> {
        return getProducts().shuffled().take(10)
    }

    suspend fun getBanner(): List<Banner> {
        return productRepository.readBannerCsv()
    }
}