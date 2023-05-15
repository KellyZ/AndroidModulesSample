package module.core.monitor.di

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import module.core.monitor.IMonitor

@InstallIn(SingletonComponent::class)
@EntryPoint
interface MonitorEntryPoint {
    fun getMonitor(): IMonitor

}

fun hiltEntryPoint(context: Context) =  EntryPointAccessors.fromApplication(context, MonitorEntryPoint::class.java)