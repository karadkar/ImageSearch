package com.rohitkaradkar.imagesearch.utils

import android.databinding.BindingAdapter
import android.view.View

/**
 * DataBinding adapter to handle visibility of the View
 */
@BindingAdapter("bind:visibleOrGone")
fun visibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}