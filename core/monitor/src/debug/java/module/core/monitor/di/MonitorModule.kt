package module.core.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import module.core.monitor.IMonitor
import module.core.monitor.MonitorDebug
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MonitorModule {
    @Binds
    @Singleton
    fun provideMonitorInterface(
        monitorDebug: MonitorDebug
    ): IMonitor
}