adb push framework.jar monkeyq.jar fastbot-thirdpart.jar max.config max.xpath.actions /sdcard/
adb push libs\arm64-v8a libs\armeabi-v7a libs\x86 libs\x86_64 /data/local/tmp/

D:\\AndroidSdk\\build-tools\\30.0.3\\aapt2.exe dump strings  ..\app\build\intermediates\apk\release\app-release.apk > max.valid.strings
adb push max.valid.strings /sdcard

adb push data/fuzzing/ /sdcard/
adb shell am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///sdcard/fuzzing