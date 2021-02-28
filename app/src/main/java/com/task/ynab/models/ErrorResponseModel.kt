package com.task.ynab.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponseModel(
    @Json(name = "error")
    val error: Error = Error()
) {
    @JsonClass(generateAdapter = true)
    data class Error(
        @Json(name = "detail")
        val detail: String = "",
        @Json(name = "id")
        val id: String = "",
        @Json(name = "name")
        val name: String = ""
    )
}