package mvp.cn.common.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
    }

    public static RefWatcher getRefWatcher(Context context) {
        QuickApplication application = (QuickApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
