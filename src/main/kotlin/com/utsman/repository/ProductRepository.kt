package com.utsman.repository

import com.utsman.entity.Product
import com.utsman.utils.readCsvList
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProductRepository {

    suspend fun readProductsCsv(): List<Product> {
        return readCsvList("products.csv") { fields ->
            val id = fields[0].trim().toInt()
            val name = fields[1].trim()
            val description = fields[2].trim()
            val price = fields[3].trim().toDouble()
            val category = fields[4].trim()
            val promoted = fields[5].trim().toBoolean()
            val images = getImages(name)

            val product = Product(id, name, description, category, price, images, promoted)
            product
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