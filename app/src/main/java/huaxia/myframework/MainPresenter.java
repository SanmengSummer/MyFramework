package huaxia.myframework;

import mvp.cn.common.util.LogUtil;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by summer on 2018/5/14.
 */

public class MainPresenter extends MvpRxPresenter<MainModel, MainView> {
    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        Observable login = getModel().getData();
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
                LogUtil.logError(loginBean+"");
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
