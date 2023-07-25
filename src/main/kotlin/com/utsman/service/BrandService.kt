package com.utsman.service

import com.utsman.entity.Brand
import com.utsman.repository.BrandRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class BrandService {

    @Inject
    private lateinit var brandRepository: BrandRepository

    suspend fun getBrand(): List<Brand> {
        return brandRepository.readBrandCsv()
    }
}