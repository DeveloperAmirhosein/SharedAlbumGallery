package com.kiliaro.project.publicpackage.imageloader

enum class ServerResizeMode(val modeName: String) {
    BoundingBox("bb"), Crop("crop"), MinimumDimension("md")
}