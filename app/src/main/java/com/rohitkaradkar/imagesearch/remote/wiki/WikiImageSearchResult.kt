package com.rohitkaradkar.imagesearch.remote.wiki

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.databind.JsonNode
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class WikiImageSearchResult {

    @JsonIgnore
    private val results = ArrayList<ImageResult>()

    fun getResults(): List<ImageResult> = results

    @JsonSetter("query")
    fun querySetter(queryNode: JsonNode) {
        if (queryNode.has("pages")) {
            val pages: JsonNode = queryNode.get("pages")

            pages.fields().forEach { pageMap ->
                pageMap.value?.also { page ->
                    val title = page.get("title")?.textValue() ?: ""
                    val thumbnail = page.get("thumbnail")

                    if (title.isNotEmpty() && thumbnail != null) {
                        val url = thumbnail.get("source")?.textValue() ?: ""
                        results.add(ImageResult(title, url))
                    }
                }
            }
        }
    }
}