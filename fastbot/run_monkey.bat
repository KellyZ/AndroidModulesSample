# adb -s device_vendor_id shell CLASSPATH=/sdcard/monkeyq.jar:/sdcard/framework.jar:/sdcard/fastbot-thirdpart.jar exec app_process /system/bin com.android.commands.monkey.Monkey -p package_name --agent reuseq --running-minutes duration(min) --throttle delay(ms) -v -v
adb shell CLASSPATH=/sdcard/monkeyq.jar:/sdcard/framework.jar:/sdcard/fastbot-thirdpart.jar exec app_process /system/bin com.android.commands.monkey.Monkey -p com.panda.template --agent reuseq --running-minutes 10 --throttle 800 --bugreport --output-directory /sdcard/fastbot/ -v -v