package summer.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import summer.app.DaggerAComponent;
import summer.app.R;
import summer.framework.base.BaseFragment;
import summer.framework.component.AppComponent;
import summer.framework.utils.LogUtil;
import summer.framework.utils.ToastUtil;

/**
 * Created by Summer on 2018/5/24.
 */

public class FirstFragment extends BaseFragment implements FirstFragmentContract.View {
    @Inject
    FirstFragmentPresenter mPresenter;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.button)
    Button button;

    @Override
    public int getLayoutResId(Bundle savedInstanceState) {
        return R.layout.fragment1;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        ll.setBackgroundColor(Color.BLACK);
        mPresenter.attachView(this);
        mPresenter.login();
    }

    @Override
    public void loginSuccess() {
        LogUtil.d("loginSuccess");
    }

    @Override
    public void complete() {
        LogUtil.d("complete");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ToastUtil.showLongToast("but");
    }
}
