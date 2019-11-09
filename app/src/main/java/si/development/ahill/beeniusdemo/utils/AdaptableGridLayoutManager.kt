package si.development.ahill.beeniusdemo.utils

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import kotlin.math.max

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AdaptableGridLayoutManager : GridLayoutManager {

    private var columnWidth: Int = 0
    private var isColumnWidthChanged: Boolean = true

    constructor(
        context: Context,
        columnWidth: Int = 0
    ) : super(context, 1) {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    constructor(
        context: Context,
        columnWidth: Int,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, 1, orientation, reverseLayout) {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        context?.let { setColumnWidth(checkedColumnWidth(it, columnWidth)) }
    }

    private fun checkedColumnWidth(context: Context, columnWidth: Int): Int =
        if (columnWidth <= 0) {
            val dpWidth = context.resources.configuration.screenWidthDp
            val minElementWidthSpanCount = dpWidth / 120
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