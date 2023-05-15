package com.panda.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.panda.template.ui.ComposeStuApp
import com.panda.template.ui.theme.TemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeLayoutStu : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FrameDecoratorProxy.checkOverlayPermission(this)
        setContent {
            TemplateTheme {
                ComposeStuApp()
            }
        }
    }

}
