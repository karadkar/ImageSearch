package com.rohitkaradkar.imagesearch.remote.wiki

import com.fasterxml.jackson.databind.ObjectMapper
import com.rohitkaradkar.imagesearch.utils.JsonUtils
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

import org.junit.Test

class WikiImageSearchResultTest {

    @Test
    fun jsonParsing() {
        val jsonData = JsonUtils.readJsonFile("mockApi/wikiImageSearchResult.json")
        assertTrue(jsonData.isNotEmpty())

        val wikiImageSearchResult: WikiImageSearchResult =
            ObjectMapper().readValue<WikiImageSearchResult>(jsonData, WikiImageSearchResult::class.java)

        assertNotNull(wikiImageSearchResult)
        assertTrue(wikiImageSearchResult.getResults().isNotEmpty())
        wikiImageSearchResult.getResults().forEach { result ->
            assertTrue(result.url.isNotEmpty())
            assertTrue(result.url.startsWith("https://")) // fixme: add url pattern matching
            assertTrue(result.title.isNotEmpty())
        }
    }
}