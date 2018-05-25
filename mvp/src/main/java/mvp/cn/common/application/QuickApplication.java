package mvp.cn.common.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import mvp.cn.common.util.AppUtil;
import mvp.cn.common.util.LogUtil;
import mvp.cn.common.util.SharedPreferencesUtil;

public abstract class QuickApplication extends Application {

    private static QuickApplication sInstance;

    public static QuickApplication getInstance() {
        return sInstance;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        LogUtil.init(this);
        AppUtil.init(this);
        initPrefs();
    }

    public static RefWatcher getRefWatcher(Context context) {
        QuickApplication application = (QuickApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }
}
