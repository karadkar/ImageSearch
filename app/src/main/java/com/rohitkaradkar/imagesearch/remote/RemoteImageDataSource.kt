package com.rohitkaradkar.imagesearch.remote

import com.rohitkaradkar.imagesearch.remote.wiki.ImageResult
import io.reactivex.Single

class RemoteImageDataSource(private val wikiImageSearchService: WikiImageSearchService) {

    fun searchImage(searchQuery: String, thumbnailSize: Int): Single<List<ImageResult>> {
        val queries = HashMap<String, String>().apply {
            put("action", "query")
            put("prop", "pageimages")
            put("format", "json")
            put("piprop", "thumbnail")
            put("pithumbsize", thumbnailSize.toString())
            put("pilimit", "50")
            put("generator", "prefixsearch")
            put("gpssearch", searchQuery)
        }
        return wikiImageSearchService.searchImages(queries)
            .map { it.getResults() }
    }
}