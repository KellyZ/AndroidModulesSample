package module.core.monitor.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import module.core.monitor.*

@Module
@InstallIn(SingletonComponent::class)
interface MonitorModule {
    @Binds
    fun provideMonitorInterface(
        monitorRelease: MonitorRelease
    ): IMonitor
}