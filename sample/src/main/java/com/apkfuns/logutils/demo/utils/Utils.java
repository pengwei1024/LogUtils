package com.apkfuns.logutils.demo.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

public class Utils {
    /**
     * 获取当前的进程名称
     *
     * @param context context
     * @return processName
     */
    public static String getProcessName(@NonNull Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }
}
