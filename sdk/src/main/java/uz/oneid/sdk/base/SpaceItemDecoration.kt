package uz.oneid.sdk.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration(

    private val top: Int = 0,
    private val firstTop: Int = top,
    private val lastTop: Int = top,

    private val start: Int = 0,
    private val firstStart: Int = start,
    private val lastStart: Int = start,

    private val end: Int = 0,
    private val firstEnd: Int = end,
    private val lastEnd: Int = end,

    private val bottom: Int = 0,
    private val firstBottom: Int = bottom,
    private val lastBottom: Int = bottom,


    ) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.top = firstTop
                outRect.left = firstStart
                outRect.right = firstEnd
                outRect.bottom = firstBottom
            }
            parent.adapter?.itemCount?.minus(1) -> {
                outRect.top = lastTop
                outRect.left = lastStart
                outRect.right = lastEnd
                outRect.bottom = lastBottom
            }
            else -> {
                outRect.top = top
                outRect.left = start
                outRect.right = end
                outRect.bottom = bottom
            }
        }

    }
}