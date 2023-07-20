package com.utsman.repository

import com.utsman.entity.Product
import com.utsman.utils.readCsvList
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProductRepository {

    suspend fun readProductsCsv(): List<Product> {
        return readCsvList("products.csv") { fields ->
            val id = fields[0].trim()
            val name = fields[1].trim()
            val category = fields[2].trim()
            val description = fields[3].trim()
            val price = fields[4].trim().toDouble()
            val image = fields[5].trim()

            val product = Product(id, name, description, category, price, image)
            product
        }
    }
}