package com.rohitkaradkar.imagesearch.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * ItemDecorator for two column LayoutManager
 */
class TwoColumnItemSpaceDecorator(private val space: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.top = space
        val position = parent.getChildLayoutPosition(view)
        if (position % 2 == 0) {
            // add lest & right margin to first row items
            outRect.left = space
            outRect.right = space
        } else {
            // add only right margin to second row items
            outRect.left = 0
            outRect.right = space
        }
    }
}
