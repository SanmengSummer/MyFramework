package summer.framework.application;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;


public class BaseApplication extends QuickApplication {
    /**
     * 存放活动状态的(未被销毁)的Activity列表
     */
    public static List<Activity> unDestroyActivityList = new ArrayList<>();
    public static BaseApplication baseApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    /**
     * 得到系统的版本号
     *
     * @return
     */
    public String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 得到应用的版本号
     *
     * @return
     */
    public int getAppVersionCode() {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        int versionCode = 0;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 得到应用的版本号
     *
     * @return
     */
    public String getAppVersionName() {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String versionCode = "";
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionCode = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 退出应用
     */
    public void quit() {
        for (Activity activity : unDestroyActivityList) {
            if (null != activity) {
                activity.finish();
            }
        }
        unDestroyActivityList.clear();
    }
}

