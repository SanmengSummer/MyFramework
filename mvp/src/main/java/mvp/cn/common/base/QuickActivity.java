package mvp.cn.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import mvp.cn.common.application.QuickApplication;

/**
 *
 */
public class QuickActivity extends FragmentActivity {

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QuickApplication.getInstance().getRefWatcher(this).watch(this);
    }
}
