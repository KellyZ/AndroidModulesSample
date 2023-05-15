package module.core.monitor

import android.app.Application
import javax.inject.Inject

class MonitorDebug @Inject constructor():IMonitor {

    @Inject
    lateinit var leakcanaryDebug: LeakcanaryDebug

    override fun initLeakCanary() {
        leakcanaryDebug.init()
    }

    override fun initMatrix(application: Application) {
        MatrixInit.init(application)
    }
}