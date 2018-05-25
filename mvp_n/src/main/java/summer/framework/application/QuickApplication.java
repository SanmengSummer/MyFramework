package summer.framework.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import summer.framework.base.Constant;
import summer.framework.component.AppComponent;
import summer.framework.component.DaggerAppComponent;
import summer.framework.module.AppModule;
import summer.framework.module.BaseApiModule;
import summer.framework.utils.AppUtil;
import summer.framework.utils.LogUtil;
import summer.framework.utils.SharedPreferencesUtil;

public abstract class QuickApplication extends Application {
    private static QuickApplication sInstance;
    private AppComponent appComponent;

    public static QuickApplication getInstance() {
        return sInstance;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        refWatcher = LeakCanary.install(this);
        initComponent();
        AppUtil.init(this);
        initPrefs();
        initNightMode();
    }

    public static RefWatcher getRefWatcher(Context context) {
        QuickApplication application = (QuickApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .baseApiModule(new BaseApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.IS_NIGHT, false);
        LogUtil.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
