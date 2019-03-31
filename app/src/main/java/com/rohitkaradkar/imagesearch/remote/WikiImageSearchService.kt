package com.rohitkaradkar.imagesearch.remote

import com.rohitkaradkar.imagesearch.remote.wiki.WikiImageSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WikiImageSearchService {
    @GET("/w/api.php")
    fun searchImages(@QueryMap queries: Map<String, String>): Single<WikiImageSearchResult>
}