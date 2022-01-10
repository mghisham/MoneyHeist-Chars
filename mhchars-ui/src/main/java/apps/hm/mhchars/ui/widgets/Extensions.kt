package apps.hm.mhchars.ui.widgets

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import apps.hm.mhchars.ui.R

/**
 * Extension function to apply the color theme to SwipeRefreshLayout.
 */
fun SwipeRefreshLayout.applyTheme() {
    setColorSchemeResources(
        R.color.planePrimaryTextColor,
        R.color.colorCardBg1,
        R.color.colorCardBg2
    )
    setProgressBackgroundColorSchemeResource(R.color.myBackground)
}

/**
 * Extension function to handle view clicks safely.
 * @param onSafeClick
 */
fun View.setOnSafeClickListener(
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

/**
 * Extension function to handle view clicks safely with a time interval.
 * @param interval time of interval
 * @param onSafeClick callback/Lambda
 */
fun View.setOnSafeClickListener(
    interval: Int,
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener(interval) { v ->
        onSafeClick(v)
    })
}