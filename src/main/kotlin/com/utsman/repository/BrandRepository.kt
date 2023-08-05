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
            val logo = getLogo(name)

            Brand(id, name, description, image, logo)
        }
    }

    private fun getLogo(name: String): String {
        val originUrl = "https://utsmannn.github.io/images/logo/{brand}_png.png"
        return originUrl.replace("{brand}", name.lowercase().replace(" ", "_"))
    }
}