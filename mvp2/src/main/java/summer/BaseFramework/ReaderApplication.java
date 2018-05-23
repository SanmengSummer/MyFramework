
package summer.BaseFramework;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import summer.BaseFramework.base.Constant;
import summer.BaseFramework.component.AppComponent;
import summer.BaseFramework.component.DaggerAppComponent;
import summer.BaseFramework.module.AppModule;
import summer.BaseFramework.module.BaseApiModule;
import summer.BaseFramework.utils.AppUtils;
import summer.BaseFramework.utils.LogUtils;
import summer.BaseFramework.utils.SharedPreferencesUtil;

/**
 * Summer
 */
public class ReaderApplication extends Application {

    private static ReaderApplication sInstance;
    private AppComponent appComponent;

    private RefWatcher refWatcher;

    //Fragment内存泄漏检查须加getRefWatcher(context).watch().
    public static RefWatcher getRefWatcher(Context context) {
        ReaderApplication application = (ReaderApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        sInstance = this;
        initComponent();
        AppUtils.init(this);
        initPrefs();
        initNightMode();
    }

    public static ReaderApplication getsInstance() {
        return sInstance;
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
        LogUtils.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
