package com.rohitkaradkar.imagesearch.utils

import okio.Okio
import java.nio.charset.StandardCharsets

object JsonUtils {
    // read file from resources
    fun readJsonFile(fileName: String): String {
        val inputStream = javaClass.classLoader
            .getResourceAsStream(fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }
}