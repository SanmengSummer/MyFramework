package summer.app.activity;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import summer.app.SystemParamModel;
import summer.framework.base.RxPresenter;
import summer.framework.network.NetApi;
import summer.framework.utils.LogUtil;

/**
 * @author Summer.
 * @date 2016/8/3.
 */
public class MainActivityPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {

    private NetApi netApi;

    @Inject
    public MainActivityPresenter(NetApi netApi) {
        this.netApi = netApi;
    }

    @Override
    public void login() {
        Subscription rxSubscription = netApi.queryParamInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SystemParamModel>() {
                    @Override
                    public void onNext(SystemParamModel data) {
                        if (data != null && mView != null) {
                            mView.loginSuccess();
                            LogUtil.e(data.toString());
                        }
                    }

                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("" + e.toString());
                    }
                });
        addSubscribe(rxSubscription);
    }

}
