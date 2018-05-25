package summer.myframework.fragment;

import android.os.Bundle;
import android.view.View;

import summer.myframework.R;
import mvp.cn.common.application.QuickApplication;
import mvp.cn.common.base.BaseFragment;

/**
 * Created by Summer on 2018/5/15.
 */

public class HomeFragment extends BaseFragment<HomeFragmentModel, HomeFragmentView, HomeFragmentPresenter> implements HomeFragmentView {
    @Override
    public HomeFragmentModel createModel() {
        return new HomeFragmentModelImpl();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
    }

    @Override
    public void initView(View v) {
    }

    @Override
    public HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter();
    }
}
