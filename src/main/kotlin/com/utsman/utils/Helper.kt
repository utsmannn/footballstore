package com.utsman.utils

import com.utsman.entity.ProductException
import com.utsman.entity.response.BaseResponse
import com.utsman.entity.response.Paged
import jakarta.ws.rs.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun <T: Any>T?.toResponse(): BaseResponse<T> {
    if (this == null) throw BadRequestException()
    return BaseResponse(this).also {
        it.status = true
        it.message = "Success"
    }
}

fun <T: Any>Any.readCsvList(name: String, mapper: (fields: List<String>) -> T): List<T> {
    val reader = this.javaClass.classLoader.getResourceAsStream(name)
    val stream = reader?.bufferedReader()
    val header = stream?.readText().orEmpty()

    val data = header.lineSequence()
        .filter { it.isNotBlank() }
        .filterIndexed { index, _ -> index != 0 }
        .map { s ->
            val fields = s.split(",")
            mapper.invoke(fields)
        }.toList()

    return data
}

fun <T>List<T>.generatePaged(page: Int, pageSize: Int): Paged<T> {
    val startIndex = (page - 1) * pageSize
    val endIndex = startIndex + pageSize
    val items = this.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(this.size))
    if (items.isEmpty()) throw ProductException.NotFound()

    val nextIndex = this.indexOf(items.lastOrNull()) +1
    val hasNextProduct = this.getOrNull(nextIndex) != null

    return Paged(page, pageSize, hasNextProduct, items)
}

fun <T, U>Paged<T>.map(mapper: (T) -> U): Paged<U> {
    return Paged(
        page, perPage, hasNextPage, data.map(mapper)
    )
}