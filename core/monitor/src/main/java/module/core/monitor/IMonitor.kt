package module.core.monitor

import android.app.Application

interface IMonitor {

    fun initLeakCanary()

    fun initMatrix(application: Application)

}