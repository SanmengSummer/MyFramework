package mvp.cn.common.base;

import android.support.v4.app.FragmentActivity;

import mvp.cn.common.application.QuickApplication;

/**
 *
 */
public class QuickActivity extends FragmentActivity {

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QuickApplication.getInstance().getRefWatcher(this).watch(this);
    }
}
