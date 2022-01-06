package apps.hm.mhchars.ui.widgets

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import apps.hm.mhchars.ui.R

fun SwipeRefreshLayout.applyTheme() {
    setColorSchemeResources(
        R.color.planePrimaryTextColor,
        R.color.colorCardBg1,
        R.color.colorCardBg2
    )
    setProgressBackgroundColorSchemeResource(R.color.myBackground)
}

fun View.setOnSafeClickListener(
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

fun View.setOnSafeClickListener(
    interval: Int,
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener(interval) { v ->
        onSafeClick(v)
    })
}