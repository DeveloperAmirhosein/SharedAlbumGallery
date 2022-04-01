package com.kiliaro.project.publicpackage.entities

import com.squareup.moshi.Json

data class PhotoEntity(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "user_id") val userId: String?,
    @field:Json(name = "media_type") val mediaType: String?,
    @field:Json(name = "filename") val filename: String?,
    @field:Json(name = "size") val size: Int?,
    @field:Json(name = "created_at") val createdAt: String?,
    @field:Json(name = "taken_at") val takenAt: Any?,
    @field:Json(name = "guessed_taken_at") val guessedTakenAt: Any?,
    @field:Json(name = "md5sum") val md5Sum: String?,
    @field:Json(name = "content_type") val contentType: String?,
    @field:Json(name = "video") val video: Any?,
    @field:Json(name = "thumbnail_url") val thumbnailUrl: String?,
    @field:Json(name = "download_url") val downloadUrl: String?,
    @field:Json(name = "resx") val resX: Int?,
    @field:Json(name = "resy") val resY: Int?,
)
