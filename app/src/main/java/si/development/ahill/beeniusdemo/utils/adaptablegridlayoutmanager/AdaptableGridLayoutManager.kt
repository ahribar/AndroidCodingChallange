package si.development.ahill.beeniusdemo.utils.adaptablegridlayoutmanager

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import kotlin.math.max

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AdaptableGridLayoutManager(
    context: Context,
    columnWidth: Int = 0
) : GridLayoutManager(context, 1) {

    private var columnWidth: Int = 0
    private var isColumnWidthChanged: Boolean = true

    init {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    private fun checkedColumnWidth(context: Context, columnWidth: Int): Int =
        if (columnWidth <= 0) {
            val dpWidth = context.resources.configuration.screenWidthDp
            val minElementWidthSpanCount = dpWidth / MIN_ELEMENT_WIDTH_DP
            this.spanCount = max(2, minElementWidthSpanCount)
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (dpWidth.toFloat() / minElementWidthSpanCount.toFloat()),
                context.resources.displayMetrics
            ).toInt()
        } else {
            columnWidth
        }

    private fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth
            isColumnWidthChanged = true
        }
    }
}