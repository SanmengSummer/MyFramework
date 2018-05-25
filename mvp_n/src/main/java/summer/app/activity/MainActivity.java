package summer.app.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import summer.app.DaggerAComponent;
import summer.app.R;
import summer.app.fragment.FirstFragment;
import summer.app.fragment.SecondFragment;
import summer.framework.base.BaseActivity;
import summer.framework.component.AppComponent;
import summer.framework.utils.LogUtil;

public class MainActivity extends BaseActivity implements MainContract.View {
    @Inject
    MainActivityPresenter mPresenter;
    @Bind(R.id.btn)
    Button btn;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private FragmentTransaction transaction;
    private FragmentManager manager;
    private boolean isSecond;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mPresenter.attachView(this);
        btn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        initFragment();
    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (null == secondFragment) secondFragment = new SecondFragment();
        if (null == firstFragment) firstFragment = new FirstFragment();
        transaction.add(R.id.fl, secondFragment);
        transaction.hide(secondFragment);
        transaction.add(R.id.fl, firstFragment);
        transaction.commit();
    }

    @Override
    public void loginSuccess() {
        LogUtil.e("loginSuccess");
    }

    @Override
    public void showFirst() {
        transaction = manager.beginTransaction();
        transaction.show(firstFragment);
        transaction.hide(secondFragment);
        transaction.commit();
    }

    @Override
    public void showSecond() {
        transaction = manager.beginTransaction();
        transaction.show(secondFragment);
        transaction.hide(firstFragment);
        transaction.commit();
    }

    @Override
    public void complete() {
        LogUtil.e("complete");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mPresenter.login();
        if (isSecond) {
            showSecond();
        } else {
            showFirst();
        }
        isSecond = !isSecond;
    }
}
