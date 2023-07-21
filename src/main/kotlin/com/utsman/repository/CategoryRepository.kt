package com.utsman.repository

import com.utsman.entity.Category
import com.utsman.utils.readCsvList
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CategoryRepository {

    suspend fun readCategoriesCsv(): List<Category> {
        return readCsvList("categories.csv") { fields ->
            val id = fields[0].trim().toInt()
            val name = fields[1].trim()
            val description = fields[2].trim()
            Category(id, name, description)
        }
    }
}