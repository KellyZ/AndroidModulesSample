package module.core.monitor.matrix

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.tencent.matrix.trace.view.FrameDecorator
import module.core.common.util.Utils

object FrameDecoratorProxy {

    private var frameDecorator: FrameDecorator? = null

    fun show(context: Context){
        if (Utils.canDrawOverlay(context)){
            if (frameDecorator==null) {
                frameDecorator = FrameDecorator.getInstance(context.applicationContext)
            }
            frameDecorator?.show()
        }
    }

    fun dismiss() {
        frameDecorator?.dismiss()
    }

    fun checkOverlayPermission(activity: ComponentActivity) {
        if (!Utils.canDrawOverlay(activity)) {
            requestWindowPermission((activity as ComponentActivity))
        }
    }

    private fun requestWindowPermission(activity: ComponentActivity) {
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it && Utils.canDrawOverlay(activity)) {
                frameDecorator?.show()
            } else {
                Toast.makeText(activity, "fail to request ACTION_MANAGE_OVERLAY_PERMISSION", Toast.LENGTH_LONG).show()
            }
        }
    }
}