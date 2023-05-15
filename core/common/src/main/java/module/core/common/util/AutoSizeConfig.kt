package module.core.common.util

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import javax.annotation.Nonnull

object AutoSizeConfig {

    var DESIGN_WIDTH_DP: Int = 360
    var sNonCompatDensity: Float = 0f
    var sNoncompatScaledDensity: Float = 0f

    fun setCustomDensity( activity: Activity?, @Nonnull context: Context) {
        var appDisplayMetrics = context.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity
            context.registerComponentCallbacks(
                object: ComponentCallbacks {
                    override fun onConfigurationChanged(newConfig: Configuration) {
                        if (newConfig.fontScale > 0){
                            sNoncompatScaledDensity = context.resources.displayMetrics.scaledDensity
                        }
                    }

                    override fun onLowMemory() {
                    }

                }
            )
        }

        var targetDensity:Float = appDisplayMetrics.widthPixels / DESIGN_WIDTH_DP * 1.0f
        var targetScaledDensity: Float = targetDensity * (sNoncompatScaledDensity / sNonCompatDensity)
        var targetDensityDpi: Int = (160 * targetDensity).toInt()

        Log.w("DensityConfig", "targetDensity ${targetDensity}, targetScaledDensity ${targetScaledDensity}, targetDensityDpi ${targetDensityDpi} ")

        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity
        appDisplayMetrics.densityDpi = targetDensityDpi

        activity?.apply {
            var activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = targetDensity
            activityDisplayMetrics.scaledDensity = targetScaledDensity
            activityDisplayMetrics.densityDpi = targetDensityDpi
        }
    }

}