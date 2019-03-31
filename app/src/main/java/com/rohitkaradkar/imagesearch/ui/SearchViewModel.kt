package com.rohitkaradkar.imagesearch.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rohitkaradkar.imagesearch.remote.RemoteImageDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val remoteImageDataSource: RemoteImageDataSource) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val result = MutableLiveData<SearchResult>()
    private val imageSize = 800
    fun getSearchResult(): LiveData<SearchResult> = result

    var lastSearchedQuery = ""
        private set

    fun retry() {
        search(lastSearchedQuery)
    }

    fun search(query: String?) {
        if (query != null && query.isNotEmpty()) {
            disposable.add(
                remoteImageDataSource.searchImage(query, imageSize)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        result.value = SearchResult(loading = true)
                    }
                    .subscribe({ imageResults ->
                        result.value = SearchResult(data = imageResults)
                    }, {
                        result.value = SearchResult(error = it)
                    })
            )
            lastSearchedQuery = query
        }

    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}