package com.rohitkaradkar.imagesearch.ui

import com.rohitkaradkar.imagesearch.remote.wiki.ImageResult

/**
 *  helps to view state as per search result
 *  todo: add test
 */
data class SearchResult(
    private val data: List<ImageResult> = listOf(),
    private val loading: Boolean = false,
    private val error: Throwable? = null
) {
    fun getData() = data
    fun showProgress() = loading
    fun showData() = !loading && data.isNotEmpty() && error == null
    fun showBlankSlate() = data.isEmpty() && error == null && !loading
    fun showError() = error != null && !loading
}