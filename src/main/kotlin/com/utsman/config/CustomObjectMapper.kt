package com.utsman.config

import com.fasterxml.jackson.databind.*
import io.quarkus.arc.All
import io.quarkus.jackson.ObjectMapperCustomizer
import jakarta.inject.Singleton
import jakarta.ws.rs.Produces

class CustomObjectMapper {

    @Singleton
    @Produces
    fun objectMapper(@All customizer: MutableList<ObjectMapperCustomizer>): ObjectMapper {
        val objectMapper = ObjectMapper()
        with(objectMapper) {
            propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            writerWithDefaultPrettyPrinter()
            enable(SerializationFeature.INDENT_OUTPUT)
        }

        customizer.forEach { it.customize(objectMapper) }
        return objectMapper
    }
}

