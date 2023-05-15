package module.core.monitor.matrix

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.report.Issue
import com.tencent.matrix.trace.config.SharePluginInfo
import com.tencent.matrix.util.MatrixLog
import java.lang.ref.SoftReference

class TestPluginListener(var context: Context): DefaultPluginListener(context) {

    companion object {
        const val TAG = "TestPluginListener"
    }

    private val softReference = SoftReference<Context>(context)
    private val mHandler = Handler(Looper.getMainLooper())

    override fun onReportIssue(issue: Issue?) {
        super.onReportIssue(issue)
        MatrixLog.e(TAG, issue.toString())

        mHandler.post {
            issue?.apply {
                if (SharePluginInfo.TAG_PLUGIN_FPS != tag) {
                    val message =
                        String.format("Report an issue - [tag:%s]-[%s].", tag ?: "", content ?: "")
                    val context = softReference.get()
                    context?.let {
                        Toast.makeText(it, message, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
}