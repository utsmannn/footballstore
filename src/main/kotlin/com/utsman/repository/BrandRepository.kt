package com.utsman.repository

import com.utsman.entity.Brand
import com.utsman.utils.readCsvList
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class BrandRepository {

    suspend fun readBrandCsv(): List<Brand> {
        return readCsvList("brands.csv") { field ->
            val id = field[0].trim().toInt()
            val name = field[1].trim()
            val description = field[2].trim()
            val image = field[3].trim()
            Brand(id, name, description, image)
        }
    }
}