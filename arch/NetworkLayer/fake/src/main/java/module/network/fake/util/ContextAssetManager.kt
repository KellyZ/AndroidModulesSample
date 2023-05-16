package module.network.fake.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class ContextAssetManager @Inject constructor(
    @ApplicationContext private val context: Context
    ): FakeAssetManager {

    override fun open(fileName: String): InputStream {
        return context.assets.open(fileName)
    }
}