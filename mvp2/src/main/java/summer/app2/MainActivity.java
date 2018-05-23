package summer.app2;

import android.os.Bundle;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import summer.BaseFramework.base.BaseActivity;
import summer.BaseFramework.component.AppComponent;
import summer.BaseFramework.utils.LogUtils;


public class MainActivity extends BaseActivity implements MainContract.View {
    @Inject
    MainActivityPresenter mPresenter;
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        LogUtils.e("chen", "setupActivityComponent");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mPresenter.login();
        LogUtils.e("chen", "initData");
        btn.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void configViews() {
        mPresenter.attachView(this);
        LogUtils.e("chen", "configViews");

    }

    @Override
    public void loginSuccess() {
        LogUtils.e("chen", "loginSuccess");
    }

    @Override
    public void complete() {
        LogUtils.e("chen", "complete");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        if (btn.getCurrentTextColor() == getResources().getColor(R.color.colorAccent))
            btn.setTextColor(getResources().getColor(R.color.colorPrimary));
        else btn.setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
