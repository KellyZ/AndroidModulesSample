package module.core.monitor

import android.app.Application
import android.os.Process
import leakcanary.*
import java.io.File
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.concurrent.thread

class LeakcanaryRelease @Inject constructor(
    private val application: Application
) {

    fun init() {
        val analysisClient by lazy {
            HeapAnalysisClient(
                // Use private app storage. cacheDir is never backed up which is important.
                heapDumpDirectoryProvider = { File("") },
                // stripHeapDump: remove all user data from hprof before analysis.
                config = HeapAnalysisConfig(stripHeapDump = true),
                // Default interceptors may cancel analysis for several other reasons.
                // interceptors = listOf(flagInterceptor) + HeapAnalysisClient.defaultInterceptors(this)
                interceptors = HeapAnalysisClient.defaultInterceptors(application)
            )
        }

        // Delete any remaining heap dump (if we crashed)
        analysisExecutor.execute {
            analysisClient.deleteHeapDumpFiles()
        }

        // Starts heap analysis on background importance
        BackgroundTrigger(
            application = application,
            analysisClient = analysisClient,
            analysisExecutor = analysisExecutor,
            analysisCallback = analysisCallback
        ).start()

        // Starts heap analysis when screen off
        ScreenOffTrigger(
            application = application,
            analysisClient = analysisClient,
            analysisExecutor = analysisExecutor,
            analysisCallback = analysisCallback
        ).start()
    }

    private val analysisExecutor = Executors.newSingleThreadExecutor {
        thread(start = false, name = "Heap analysis executor") {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
            it.run()
        }
    }

    private val analysisCallback: (HeapAnalysisJob.Result) -> Unit = { result ->
        if (result is HeapAnalysisJob.Result.Done) {
//            uploader.upload(result.analysis)
        }
    }

    private val uploader by lazy {
//        BugsnagLeakUploader(this@ReleaseExampleApplication)
    }
}