package com.rohitkaradkar.imagesearch.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rohitkaradkar.imagesearch.R
import com.rohitkaradkar.imagesearch.databinding.ActivitySearchDetailBinding
import com.squareup.picasso.Picasso

class SearchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySearchDetailBinding>(this, R.layout.activity_search_detail)

        val title = intent?.extras?.getString(keyTitle) ?: ""
        val imageUrl = intent?.extras?.getString(keyImageUrl) ?: ""

        supportActionBar?.also { actionBar ->
            actionBar.title = title
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        Picasso.get()
            .load(imageUrl)
            .into(binding.ivDetailImage)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val keyTitle = "key.image.title"
        const val keyImageUrl = "key.image.url"
    }
}
