package huaxia.myframework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import huaxia.myframework.fragment.HomeFragment;
import huaxia.myframework.R;
import mvp.cn.common.base.BaseActivity;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView {


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
        getData();
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
        findViewById(R.id.id).setBackgroundColor(Color.BLACK);
    }

    public void b(View view) {
        setBackgroundBlack();
    }
}
