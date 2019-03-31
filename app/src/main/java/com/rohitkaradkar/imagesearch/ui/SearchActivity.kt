package com.rohitkaradkar.imagesearch.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.widget.SearchView
import com.rohitkaradkar.imagesearch.R
import com.rohitkaradkar.imagesearch.databinding.ActSearchBinding
import com.rohitkaradkar.imagesearch.utils.TwoColumnItemSpaceDecorator
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: SearchAdapter
    private lateinit var binding: ActSearchBinding
    private val spanCount = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.act_search)

        adapter = SearchAdapter(this) { clickedImageResult ->
            val intent = Intent(this, SearchDetailActivity::class.java).apply {
                putExtra(SearchDetailActivity.keyImageUrl, clickedImageResult.url)
                putExtra(SearchDetailActivity.keyTitle, clickedImageResult.title)
            }
            startActivity(intent)
        }

        binding.rvSearchedImages.adapter = adapter
        binding.rvSearchedImages.layoutManager = GridLayoutManager(this, spanCount)

        val itemSpace = resources.getDimensionPixelSize(R.dimen.item_space)
        binding.rvSearchedImages.addItemDecoration(TwoColumnItemSpaceDecorator(itemSpace))

        /**
         * binding liveData[SearchResult] with layout for automatic updates
         * see [R.layout.act_search]
         */
        binding.result = viewModel.getSearchResult()
        binding.setLifecycleOwner(this)

        binding.btnRetry.setOnClickListener {
            viewModel.retry()
        }

        viewModel.getSearchResult().observe(this, Observer { searchResult ->
            if (searchResult != null) {
                // this can be done with data binding
                adapter.update(searchResult.getData())
            }
        })
    }

    override fun onResume() {
        super.onResume()
        // remove focus from searchView
        binding.container.requestFocus()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            menuInflater.inflate(R.menu.search_menu, menu)
            val searchMenu = menu.findItem(R.id.action_search)
            val searchView = searchMenu.actionView as SearchView
            searchView.maxWidth = Integer.MAX_VALUE // allows to expand fully
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.search(query)
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })

            if (viewModel.lastSearchedQuery.isNotEmpty()) {
                searchView.setQuery(viewModel.lastSearchedQuery, false)
                searchView.setIconifiedByDefault(false) // expands on rotation
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}
