package huaxia.myframework;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import butterknife.BindView;
import mvp.cn.common.base.BaseActivity;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView {

    @BindView(R.id.id)
    ConstraintLayout id;

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
        if (null == id)
            id = findViewById(R.id.id);
        id.setBackgroundColor(Color.BLACK);
    }

    public void b(View view) {
        setBackgroundBlack();
    }
}
