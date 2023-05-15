package module.core.monitor

import android.view.View
import javax.inject.Inject

class JankstatRelease @Inject constructor() {

    fun init(){}
    fun putState(view: View, key: String, value: String){}
    fun removeState(view: View, key: String){}
}