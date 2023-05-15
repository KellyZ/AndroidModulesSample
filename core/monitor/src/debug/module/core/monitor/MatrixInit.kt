package module.core.monitor

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.tencent.matrix.Matrix
import com.tencent.matrix.batterycanary.BatteryEventDelegate
import com.tencent.matrix.batterycanary.BatteryMonitorPlugin
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.tencent.matrix.lifecycle.LifecycleThreadConfig
import com.tencent.matrix.lifecycle.MatrixLifecycleConfig
import com.tencent.matrix.lifecycle.MatrixLifecycleLogger
import com.tencent.matrix.lifecycle.supervisor.SupervisorConfig
import com.tencent.matrix.memory.canary.MemoryCanaryPlugin
import com.tencent.matrix.memory.canary.trim.TrimCallback
import com.tencent.matrix.memory.canary.trim.TrimMemoryNotifier
import com.tencent.matrix.resource.ResourcePlugin
import com.tencent.matrix.resource.config.ResourceConfig
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.tencent.matrix.util.MatrixLog
import com.tencent.sqlitelint.SQLiteLint
import com.tencent.sqlitelint.SQLiteLintPlugin
import com.tencent.sqlitelint.config.SQLiteLintConfig
import module.core.monitor.matrix.*
import java.io.File

object MatrixInit {
    const val TAG = "MatrixInit"

    fun init(application: Application) {
        val dynamicConfig = DynamicConfigImplDemo()
        Log.i(TAG, "Start Matrix configurations.")
        val builder: Matrix.Builder = Matrix.Builder(application)

        // Reporter. Matrix will callback this listener when found issue then emitting it.
        builder.pluginListener(TestPluginListener(application))

//        val memoryCanaryPlugin = MemoryCanaryPlugin(MemoryCanaryBoot.configure())
//        builder.plugin(memoryCanaryPlugin)

        // Configure trace canary.
        val tracePlugin: TracePlugin = configureTracePlugin(application, dynamicConfig)
        builder.plugin(tracePlugin)

        // Configure resource canary.
//        val resourcePlugin: ResourcePlugin = configureResourcePlugin(application, dynamicConfig)
//        builder.plugin(resourcePlugin)

        // Configure io canary.
//        val ioCanaryPlugin: IOCanaryPlugin = configureIOCanaryPlugin(dynamicConfig)
//        builder.plugin(ioCanaryPlugin)

        // Configure SQLite lint plugin.
//        val sqLiteLintPlugin: SQLiteLintPlugin = configureSQLiteLintPlugin()
//        builder.plugin(sqLiteLintPlugin)

        // Configure battery canary.
//        val batteryMonitorPlugin: BatteryMonitorPlugin = configureBatteryCanary(application)
//        builder.plugin(batteryMonitorPlugin)

        builder.matrixLifecycleConfig(configureMatrixLifecycle())
        Matrix.init(builder.build())

        Matrix.with().startAllPlugins()
        MatrixLifecycleLogger.init(application, enable = true)
//        TrimMemoryNotifier.addProcessBackgroundTrimCallback(object : TrimCallback {
//            override fun systemTrim(level: Int) {
//                MatrixLog.d(TAG, "systemTrim: ")
//            }
//
//            override fun backgroundTrim() {
//                MatrixLog.d(TAG, "backgroundTrim: ")
//            }
//        })

    }

    private fun configureTracePlugin(context: Context, dynamicConfig: DynamicConfigImplDemo): TracePlugin {
        val fpsEnable: Boolean = dynamicConfig.isFPSEnable()
        val traceEnable: Boolean = dynamicConfig.isTraceEnable()
        val appMethodBeatEnable: Boolean = dynamicConfig.isAppMethodBeatEnable()
        val signalAnrTraceEnable: Boolean = dynamicConfig.isSignalAnrTraceEnable()
        val traceFileDir = File(context.filesDir, "matrix_trace")
        if (!traceFileDir.exists()) {
            if (traceFileDir.mkdirs()) {
                Log.e(TAG, "failed to create traceFileDir")
            }
        }
        val anrTraceFile = File(traceFileDir,"anr_trace") // path : /data/user/0/sample.tencent.matrix/files/matrix_trace/anr_trace
        val printTraceFile = File(traceFileDir,"print_trace") // path : /data/user/0/sample.tencent.matrix/files/matrix_trace/print_trace

        val traceConfig: TraceConfig = TraceConfig.Builder()
            .dynamicConfig(dynamicConfig)
            .enableFPS(fpsEnable)
            .enableAppMethodBeat(appMethodBeatEnable)
            .enableEvilMethodTrace(traceEnable)
            .enableAnrTrace(traceEnable)
            .enableStartup(traceEnable)
            .enableIdleHandlerTrace(traceEnable) // Introduced in Matrix 2.0
            .enableSignalAnrTrace(signalAnrTraceEnable) // Introduced in Matrix 2.0
            .anrTracePath(anrTraceFile.absolutePath)
            .printTracePath(printTraceFile.absolutePath)
            .splashActivities("sample.tencent.matrix.SplashActivity;")
            .isDebug(false)
            .isDevEnv(false)
            .build()

        //Another way to use SignalAnrTracer separately
        //useSignalAnrTraceAlone(anrTraceFile.getAbsolutePath(), printTraceFile.getAbsolutePath());
        return TracePlugin(traceConfig)
    }

    private fun configureResourcePlugin(application: Application,dynamicConfig: DynamicConfigImplDemo): ResourcePlugin {
        val intent = Intent()
        val mode = ResourceConfig.DumpMode.MANUAL_DUMP
        intent.setClassName(application.packageName, "com.tencent.mm.ui.matrix.ManualDumpActivity")
        val resourceConfig = ResourceConfig.Builder()
            .dynamicConfig(dynamicConfig)
            .setAutoDumpHprofMode(mode)
            .setManualDumpTargetActivity(ManualDumpActivity::class.java.name)
            .setManufacture(Build.MANUFACTURER)
            .build()
        ResourcePlugin.activityLeakFixer(application)
        return ResourcePlugin(resourceConfig)
    }

    private fun configureIOCanaryPlugin(dynamicConfig: DynamicConfigImplDemo):IOCanaryPlugin {
        return IOCanaryPlugin(IOConfig.Builder()
            .dynamicConfig(dynamicConfig)
            .build()
        )
    }

    private fun configureSQLiteLintPlugin(): SQLiteLintPlugin {
        val sqlLiteConfig = SQLiteLintConfig(SQLiteLint.SqlExecutionCallbackMode.CUSTOM_NOTIFY)
        return SQLiteLintPlugin((sqlLiteConfig))
    }

    private fun configureBatteryCanary(context: Context) : BatteryMonitorPlugin {
        if (!BatteryEventDelegate.isInit()){
            BatteryEventDelegate.init(context.applicationContext as Application)
        }
        return BatteryCanaryInitHelper.createMonitor(context)
    }

    private fun configureMatrixLifecycle(): MatrixLifecycleConfig {
        return MatrixLifecycleConfig(
            supervisorConfig = SupervisorConfig(
                enable = true,
                autoCreate = true,
                lruKillerWhiteList = arrayListOf()
            ),
            enableFgServiceMonitor = true,
            enableOverlayWindowMonitor = true,
            lifecycleThreadConfig = LifecycleThreadConfig(),
            enableLifecycleLogger = true
        )
    }

}

