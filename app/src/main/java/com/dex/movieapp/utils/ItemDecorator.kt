package com.dex.movieapp.utils

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecorator(val space: Int, private val numberOfColumns: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        addSpaceToView(outRect, parent?.getChildAdapterPosition(view), parent)
    }

    private fun addSpaceToView(outRect: Rect?, position: Int?, parent: RecyclerView?) {
        if (position == null || parent == null)
            return

        val grid = parent.layoutManager as GridLayoutManager
        val spanSize = grid.spanSizeLookup.getSpanSize(position)

        if (spanSize == numberOfColumns) {
            outRect?.right = space
        } else {
            var allSpanSize = 0
            for (i: Int in IntRange(0, position)) {
                allSpanSize += grid.spanSizeLookup.getSpanSize(i)
            }
            val currentModuloResult = allSpanSize % numberOfColumns
            if (currentModuloResult == 0) {
                outRect?.right = space
            }
        }
        outRect?.left = space
        outRect?.top = space
    }
}
