package summer.myframework.activity;

import mvp.cn.common.util.LogUtil;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Summer on 2018/5/14.
 * 在这里处理MainActivity的用户交互的复杂逻辑
 * 比如网络请求事件，与其他页面的数据交互事件等等
 * 涉及到控制页面view的 一般也会通过MainView进行
 */

public class MainPresenter extends MvpRxPresenter<MainModel, MainView> {
    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        Observable login = getModel().getData();//获取MainModel
        getNetWork(login, new Subscriber<SystemParamModel>() {
            @Override
            public void onCompleted() {
                LogUtil.logError("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.logError(e+"");
            }

            @Override
            public void onNext(SystemParamModel loginBean) {
                getView().setBackgroundBlack();//获取MainView
                LogUtil.logError(loginBean+"");
            }
        });
    }
}
