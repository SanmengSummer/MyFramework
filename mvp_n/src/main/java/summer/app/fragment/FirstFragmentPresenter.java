package summer.app.fragment;

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
 * Created by Summer on 2018/5/24.
 */

public class FirstFragmentPresenter extends RxPresenter<FirstFragmentContract.View> implements FirstFragmentContract.Presenter<FirstFragmentContract.View>  {
    private NetApi netApi;

    @Inject
    public FirstFragmentPresenter(NetApi netApi) {
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
