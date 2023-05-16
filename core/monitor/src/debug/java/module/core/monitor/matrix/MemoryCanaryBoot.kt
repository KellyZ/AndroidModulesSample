package module.core.monitor.matrix

import com.tencent.matrix.memory.canary.MemoryCanaryConfig

class MemoryCanaryBoot {

    companion object {
        fun configure() : MemoryCanaryConfig {
            return MemoryCanaryConfig(
                appBgSumPssMonitorConfigs = arrayOf(
                    BackgroundMemoryMonitorBoot.appStagedBgMemoryMonitorConfig,
                    BackgroundMemoryMonitorBoot.appDeepBgMemoryMonitorConfig
                ),
                processBgMemoryMonitorConfigs = arrayOf(
                    BackgroundMemoryMonitorBoot.procStagedBgMemoryMonitorConfig,
                    BackgroundMemoryMonitorBoot.procDeepBgMemoryMonitorConfig,

                    BackgroundMemoryMonitorBoot.procStagedBgMemoryMonitorConfig2,
                    BackgroundMemoryMonitorBoot.procStagedBgMemoryMonitorConfig3,
                    BackgroundMemoryMonitorBoot.procStagedBgMemoryMonitorConfig4,
                    BackgroundMemoryMonitorBoot.procStagedBgMemoryMonitorConfig5
                )
            )
        }
    }

}