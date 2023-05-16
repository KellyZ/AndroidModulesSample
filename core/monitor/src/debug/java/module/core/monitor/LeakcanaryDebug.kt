package module.core.monitor

import android.app.Application
import android.content.Context
import com.kwai.koom.base.DefaultInitTask
import com.kwai.koom.fastdump.ForkJvmHeapDumper
import dagger.hilt.android.qualifiers.ApplicationContext
import leakcanary.LeakCanary
import javax.inject.Inject

class LeakcanaryDebug @Inject constructor (
    @ApplicationContext private val context: Context
) {

    fun init() {
        DefaultInitTask.init(context as Application)
        LeakCanary.config = LeakCanary.config.copy(
            // 自定义 Heap Dump 执行器
            heapDumper = {
                ForkJvmHeapDumper.getInstance().dump(it.absolutePath)
            }
        )
    }

}