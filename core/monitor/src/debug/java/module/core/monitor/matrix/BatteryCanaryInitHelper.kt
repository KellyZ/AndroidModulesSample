package module.core.monitor.matrix

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.tencent.matrix.Matrix
import com.tencent.matrix.batterycanary.BatteryCanary
import com.tencent.matrix.batterycanary.BatteryEventDelegate
import com.tencent.matrix.batterycanary.BatteryMonitorPlugin
import com.tencent.matrix.batterycanary.monitor.BatteryMonitorCallback.BatteryPrinter
import com.tencent.matrix.batterycanary.monitor.BatteryMonitorConfig
import com.tencent.matrix.batterycanary.monitor.feature.*
import com.tencent.matrix.batterycanary.shell.TopThreadFeature
import com.tencent.matrix.batterycanary.stats.BatteryRecorder.MMKVRecorder
import com.tencent.matrix.batterycanary.stats.BatteryStats.BatteryStatsImpl
import com.tencent.matrix.batterycanary.stats.BatteryStatsFeature
import com.tencent.matrix.batterycanary.stats.HealthStatsFeature
import com.tencent.matrix.util.MatrixLog
import com.tencent.mmkv.MMKV
import java.util.concurrent.Callable


object BatteryCanaryInitHelper {
    const val TAG = "Matrix.BatteryCanaryDemo"

    var sBatteryConfig: BatteryMonitorConfig? = null

    fun createMonitor(context: Context): BatteryMonitorPlugin {
        check(sBatteryConfig == null) { "Duplicated init!" }

        // Init MMKV only when BatteryStatsFeature is & MMKVRecorder is enabled.
        MMKV.initialize(context)
        val mmkv = MMKV.mmkvWithID("battery-stats.bin", MMKV.MULTI_PROCESS_MODE)
        registerUIStat(context.getApplicationContext() as Application)
        sBatteryConfig = BatteryMonitorConfig.Builder() // Thread Activities Monitor
            .enable(JiffiesMonitorFeature::class.java)
            .enableStatPidProc(true)
            .greyJiffiesTime(3 * 1000L)
            .enableBackgroundMode(false)
            .backgroundLoopCheckTime(30 * 60 * 1000L)
            .enableForegroundMode(true)
            .foregroundLoopCheckTime(20 * 60 * 1000L)
            .setBgThreadWatchingLimit(5000)
            .setBgThreadWatchingLimit(8000) // CPU Stats
            .enable(CpuStatFeature::class.java) // App & Device Status Monitor For Better Invalid Battery Activities Configure
            .setOverHeatCount(1024)
            .enable(DeviceStatMonitorFeature::class.java)
            .enable(AppStatMonitorFeature::class.java)
            .setSceneSupplier(object : Callable<String> {
                override fun call(): String {
                    return "Current AppScene"
                }
            }) // AMS Activities Monitor:
            // alarm/wakelock watch
            .enableAmsHook(true)
            .enable(AlarmMonitorFeature::class.java)
            .enable(WakeLockMonitorFeature::class.java)
            .wakelockTimeout(2 * 60 * 1000L)
            .wakelockWarnCount(3)
            .addWakeLockWhiteList("Ignore WakeLock TAG1")
            .addWakeLockWhiteList("Ignore WakeLock TAG2") // scanning watch (wifi/gps/bluetooth)
            .enable(WifiMonitorFeature::class.java)
            .enable(LocationMonitorFeature::class.java)
            .enable(BlueToothMonitorFeature::class.java) // .enable(NotificationMonitorFeature.class)
            // BatteryStats
            .enable(BatteryStatsFeature::class.java)
            .setRecorder(MMKVRecorder(mmkv))
            .setStats(BatteryStatsImpl())
            .enable(HealthStatsFeature::class.java) // Lab Feature:
            // network monitor
            // looper task monitor
            .enable(TrafficMonitorFeature::class.java)
            .enable(LooperTaskMonitorFeature::class.java)
            .addLooperWatchList("main")
            .useThreadClock(false)
            .enableAggressive(true)
            .enable(TopThreadFeature::class.java) // Monitor Callback
            .setCallback(BatteryStatsListener())
            .build()
        return BatteryMonitorPlugin(sBatteryConfig)
    }

    private fun registerUIStat(app: Application) {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: Bundle?
            ) {}

            override fun onActivityStarted(activity: Activity) {
                BatteryCanary.getMonitorFeature(
                    BatteryStatsFeature::class.java
                ) { batteryStatsFeature ->
                    var uiName: String = activity.javaClass.name
                    val pkg: String = app.packageName
                    if (uiName.startsWith(pkg)) {
                        uiName = uiName.substring(uiName.lastIndexOf(pkg) + pkg.length)
                    }
                    batteryStatsFeature.statsScene(uiName)
                }
            }

            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle
            ) {
            }

            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    fun startBatteryMonitor(context: Context) {
        val plugin = Matrix.with().getPluginByClass(BatteryMonitorPlugin::class.java)
        if (plugin != null && !plugin.isPluginStarted) {
            if (!BatteryEventDelegate.isInit()) {
                BatteryEventDelegate.init(context.getApplicationContext() as Application)
            }
            MatrixLog.i(TAG, "plugin-battery start")
            plugin.start()
        }
    }


    class BatteryStatsListener : BatteryPrinter() {
        override fun onCanaryDump(monitors: CompositeMonitors) {
            monitors.getAppStats { appStats -> // Dump battery stats data periodically
                val statMinute = appStats.minute
                val foreground = appStats.isForeground
                val charging = appStats.isCharging
                Log.w(
                    TAG, "onDumpBatteryStatsReport, statMinute " + appStats.minute
                            + ", foreground = " + foreground
                            + ", charging = " + charging
                )
            }
            super.onCanaryDump(monitors)
        }

        override fun onCanaryReport(monitors: CompositeMonitors) {
            super.onCanaryReport(monitors)
            // Report all enabled battery canary monitors' data here
        }
    }
}