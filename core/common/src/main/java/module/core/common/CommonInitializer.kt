package module.core.monitor

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import module.core.common.util.AutoSizeConfig
import module.core.common.util.WebviewProload

class CommonInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        AutoSizeConfig.setCustomDensity(null, context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}