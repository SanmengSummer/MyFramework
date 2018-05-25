package summer.myframework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import summer.myframework.R;
import summer.myframework.fragment.HomeFragment;
import mvp.cn.common.base.BaseActivity;
import mvp.cn.common.util.ToastUtil;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.id)
    LinearLayout id;
    private boolean isRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
//        ft.add(R.id.fl, homeFragment);
//        ft.hide(homeFragment);
//        ft.show(homeFragment);
        ft.replace(R.id.fl, homeFragment);
        ft.commit();
        id.setBackgroundColor(Color.BLACK);
    }

    @Override
    public MainModel createModel() {
        return new MainModelImpl();
    }

    public void getData() {
        getPresenter().getData();
    }

    @Override
    public void setBackgroundBlack() {
        if (isRed)
            id.setBackgroundColor(Color.BLACK);
        else
            id.setBackgroundColor(Color.GRAY);
        isRed = !isRed;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn, R.id.fl, R.id.id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                ToastUtil.showToast( "mam" + isRed);
                getData();
                break;
            case R.id.fl:
                break;
            case R.id.id:
                break;
        }
    }
}
