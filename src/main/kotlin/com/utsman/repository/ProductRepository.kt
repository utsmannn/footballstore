package com.utsman.repository

import com.utsman.entity.Banner
import com.utsman.entity.Brand
import com.utsman.entity.Product
import com.utsman.utils.readCsvList
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ProductRepository {

    @Inject
    private lateinit var brandRepository: BrandRepository

    suspend fun readProductsCsv(): List<Product> {
        val brands = brandRepository.readBrandCsv()
        return readCsvList("products.csv") { fields ->
            val id = fields[0].trim().toInt()
            val name = fields[1].trim()
            val description = fields[2].trim().replace("~~", ",")
            val price = fields[3].trim().toDouble()
            val category = fields[4].trim()
            val promoted = fields[5].trim().toBoolean()
            val images = getImages(name)
            val brand = fields[6].trim()

            val (categoryId, categoryName) = category.split("-")
            val (brandId, brandName) = brand.split("-")

            val brandData = brands.find { it.id == brandId.toInt() }
            val brandLogo = brandData?.logo.orEmpty()

            val productCategory = Product.MiniCategory(categoryId.toInt(), categoryName)
            val productBrand = Product.MiniBrand(brandId.toInt(), brandName, brandLogo)

            Product(id, name, description, productCategory, price, images, promoted, productBrand)
        }
    }

    suspend fun readBannerCsv(): List<Banner> {
        return readCsvList("banner.csv") { fields ->
            val id = fields[0].trim().toInt()
            val productId = fields[1].trim().toInt()
            val productName = fields[2].trim()
            val description = fields[3].trim()
            val colorPrimary = fields[4].trim()
            val colorAccent = fields[5].trim()

            val productImage = getImages(productName).random()

            Banner(id, productId, productImage, description, colorPrimary, colorAccent)
        }
    }

    private fun getImages(name: String): List<String> {
        val images = readCsvList("images.csv") { fields ->
            val nameProduct = fields[0].trim()
            val pathProduct = fields[1].trim()
            val imageProduct = "$BASE_IMAGE_URL/$pathProduct"
            Pair(nameProduct, imageProduct)
        }

        return images.filter { it.first.lowercase() == name.lowercase() }.map { it.second }
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://utsmannn.github.io"
    }
}