package com.panda.template

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import module.core.common.util.WebviewProload
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class GlobalApplication: Application(), ImageLoaderFactory {

    @Inject
    lateinit var imageLoader: Provider<ImageLoader>

    override fun newImageLoader(): ImageLoader {
        return imageLoader.get()
    }
    override fun onCreate() {
        super.onCreate()
        WebviewProload.preloadWebView(this)
    }

}