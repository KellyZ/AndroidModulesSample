package module.core.common.util

import android.content.Context
import android.os.Build
import android.provider.Settings

object Utils {

    fun canDrawOverlay(context: Context):Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(context)
        } else {
            true
        }
    }
}