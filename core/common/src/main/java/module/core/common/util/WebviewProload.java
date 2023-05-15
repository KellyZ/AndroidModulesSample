package module.core.common.util;

import static module.core.common.util.Reflection.invokeMethod;
import static module.core.common.util.Reflection.invokeStaticMethod;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.WebView;

public class WebviewProload {

    private static final String TAG = "WebviewProload";

    public static void preloadWebView(final Application app) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                        @Override
                        public boolean queueIdle() {
                            startChromiumEngine(app);
                            return false;
                        }
                    });
                } catch (final Throwable t) {
                    Log.e(TAG, "Oops!", t);
                }
            }
        });
    }

    private static void startChromiumEngine(final Context context) {
        try {
            String processName = ProcessUtil.getCurrentProcessName(context);
            String packageName = context.getPackageName();
            Log.e(TAG, "preload chromium engine, processName "+ processName + ",packageName " + packageName);
            if (!packageName.equals(processName)) { //非主进程预加载webview
                final long t0 = SystemClock.uptimeMillis();
                final Object provider = invokeStaticMethod(Class.forName("android.webkit.WebViewFactory"), "getProvider");
                invokeMethod(provider, "startYourEngines", new Class[]{boolean.class}, new Object[]{true});
                Log.i(TAG, "Start chromium engine complete: " + (SystemClock.uptimeMillis() - t0) + " ms");
                if (Build.VERSION.SDK_INT >= 28) {
                    WebView.setDataDirectorySuffix(processName);
                }
            }
        } catch (final Throwable t) {
            Log.e(TAG, "Start chromium engine error", t);
        }
    }

}
