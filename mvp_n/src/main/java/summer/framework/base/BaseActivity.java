package summer.framework.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import summer.framework.application.BaseApplication;
import summer.framework.component.AppComponent;
import summer.framework.utils.LogUtil;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected int statusBarColor = 0;
    protected View statusBarView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setBaseData();
        initData();
    }

    private void setBaseData() {
        transparent19and20();
        mContext = this;
        ButterKnife.bind(this);
        BaseApplication.unDestroyActivityList.add(this);
        setupActivityComponent(BaseApplication.getInstance().getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutId();

    public abstract void initData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * 展示状态栏
     */
    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }

    /**
     * 得到屏幕宽度
     *
     * @return 宽度
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    /**
     * 得到屏幕高度
     *
     * @return 高度
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }

    /**
     * 是否全屏和显示标题，true为全屏和无标题，false为无标题，请在setContentView()方法前调用
     *
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        if (fullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }
    /**
     * 设置状态栏背景状态
     */
    public void setTranslucentStatus(int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(colorResId);// 状态栏无背景
    }

    public static void setStatusBarTextColor(Activity context, int type) {
        Window window = context.getWindow();
        Class clazz = window.getClass();
        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);
            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (type == 0) {
                extraFlagField.invoke(window, tranceFlag, tranceFlag);// 只需要状态栏透明
            } else if (type == 1) {
                extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag);// 状态栏透明且黑色字体
            } else {
                extraFlagField.invoke(window, 0, darkModeFlag);// 清除黑色字体
            }
        } catch (Exception e) {

        }
    }
    /**
     * 显示栈中的activity, 并干掉它上边的
     */
    public boolean finishActivityAndAboveIt(String activityName) {

        synchronized (BaseApplication.class) {
            if (activityName == null) {
                return false;
            }
            boolean isExist = false;
            for (Activity act : BaseApplication.unDestroyActivityList) {
                if (act.getClass().getName().equals(activityName)) {
                    isExist = true;
                }
            }
            if (!isExist) {
                LogUtil.i("栈中没有这个Activiy:" + activityName);
                return false;
            }
            boolean isOk = false;
            while (!isOk) {
                String prepareFinishActName = BaseApplication.unDestroyActivityList.get(BaseApplication.unDestroyActivityList.size() - 1).getClass().getName();
                BaseApplication.unDestroyActivityList.remove(BaseApplication.unDestroyActivityList.size() - 1).finish();
                LogUtil.i("栈中activity数量:" + BaseApplication.unDestroyActivityList.size());
                if (activityName.equals(prepareFinishActName)) {
                    isOk = true;
                }
            }
            return true;
        }
    }
}
