package module.core.monitor

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import module.core.monitor.di.hiltEntryPoint

class MonitorInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        val monitor = hiltEntryPoint(context).getMonitor()
        monitor.initLeakCanary()
        monitor.initMatrix(context.applicationContext as Application)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}

