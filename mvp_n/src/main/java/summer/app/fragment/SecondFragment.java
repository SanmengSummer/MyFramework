package summer.app.fragment;

import android.os.Bundle;
import android.view.View;

import summer.app.R;
import summer.framework.base.BaseFragment;
import summer.framework.component.AppComponent;

/**
 * Created by Summer on 2018/5/24.
 */

public class SecondFragment extends BaseFragment {

    @Override
    public int getLayoutResId(Bundle savedInstanceState) {
        return R.layout.fragment1;
    }

    @Override
    public void initView(View v) {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
