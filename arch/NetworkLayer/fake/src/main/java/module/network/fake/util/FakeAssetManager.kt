package module.network.fake.util

import java.io.InputStream

interface FakeAssetManager {
    fun open(fileName: String): InputStream
}