
-optimizationpasses 1

-printconfiguration build/reports/configuration.txt
# 当您在启用 R8 的情况下构建应用时，R8 会按照您指定的路径和文件名输出报告。移除的代码的报告与以下输出类似
-printusage build/reports/usage.txt
# 查看 R8 根据项目的保留规则确定的入口点的报告
-printseeds build/reports/seeds.txt

#支持对应用的堆栈轨迹进行轨迹还原
-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile

-dontwarn com.squareup.haha.**

-keep class com.tencent.matrix.** { *; }
-keep class module.data.model.** { *; }
-keep class module.network.model.** { *; }