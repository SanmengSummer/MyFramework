package mvp.cn.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvp.cn.common.application.SoftApplication;
import mvp.cn.common.util.LogUtil;
import mvp.cn.rx.MvpModel;
import mvp.cn.rx.MvpRxFragment;
import mvp.cn.rx.MvpRxPresenter;

/**
 * Created by Summer on 2016/5/18.
 */
public abstract class BaseFragment<M extends MvpModel, V extends MvpView, P extends MvpRxPresenter<M, V>>
        extends MvpRxFragment<M,V,P> {
    protected SoftApplication softApplication;
    private View inflate;
    private int contentViewRes = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        softApplication = SoftApplication.softApplication;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (inflate == null) {
            LogUtil.log(getClass().getName() + "初始化");
            setContentLayout(savedInstanceState);
            if (contentViewRes == -1) {
                LogUtil.log("未设置布局");
                return null;
            }
            inflate = inflater.inflate(contentViewRes, null);
            if (inflate != null)
                initView(inflate);
        } else {
            LogUtil.log(getClass().getName() + "再次加载,无需初始化");
        }

        return inflate;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log(getClass().getName() + "[onDestroy]");
    }

    public abstract void setContentLayout(Bundle savedInstanceState);

    public abstract void initView(View v);

    public void setContentView(int resId) {
        this.contentViewRes = resId;
    }
}
