package cn.zhoujianwen.huolajianshen.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import cn.zhoujianwen.huolajianshen.base.BaseApplication;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class SystemUtils {

    private static PackageManager pm;
    private static PackageInfo packageInfo;
    private static String versionName;

    /**
     * 得到版本名
     *
     * @return
     */
    public static String getVersionName() {
        // 得到包名信息等，检测是否需要更新,显示版本号等
        pm = BaseApplication.getApplication().getPackageManager();
        try {
            packageInfo = pm.getPackageInfo(BaseApplication.getApplication().getPackageName(), 0);
            int currentVersionCode = packageInfo.versionCode;
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }
}
