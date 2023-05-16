package module.core.monitor.matrix

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.tencent.matrix.resource.config.SharePluginInfo
import module.core.monitor.R

class ManualDumpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val leakActivityName = intent.getStringExtra(SharePluginInfo.ISSUE_ACTIVITY_NAME)
        val leakProcess = intent.getStringExtra(SharePluginInfo.ISSUE_LEAK_PROCESS)
        val hrofPath = intent.getStringExtra(SharePluginInfo.ISSUE_HPROF_PATH)
        val refChain = intent.getStringExtra(SharePluginInfo.ISSUE_LEAK_DETAIL)

        setContentView(R.layout.leak_matrix_dump)
        findViewById<TextView>(R.id.leak_activity_name).text = leakActivityName
        findViewById<TextView>(R.id.leak_process).text = leakProcess
        findViewById<TextView>(R.id.hprof_path).text = hrofPath
        findViewById<TextView>(R.id.leak_refchain).text = refChain
    }
}