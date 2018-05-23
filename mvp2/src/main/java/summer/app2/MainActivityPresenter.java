
package summer.app2;

import javax.inject.Inject;

import summer.BaseFramework.api.NetApi;
import summer.BaseFramework.base.RxPresenter;
import summer.BaseFramework.utils.LogUtils;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                            LogUtils.e(data.toString());
                        }
                    }

                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("" + e.toString());
                    }
                });
        addSubscribe(rxSubscription);
    }

}
