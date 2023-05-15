package module.core.monitor

import android.app.Application
import javax.inject.Inject

class MonitorRelease @Inject constructor() :IMonitor {

    @Inject
    lateinit var leakcanaryRelease: LeakcanaryRelease

    override fun initLeakCanary() {
        leakcanaryRelease.init()
    }

    override fun initMatrix(application: Application) {
    }
}