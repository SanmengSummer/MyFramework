package mvp.cn.rx;


import mvp.cn.common.base.MvpBasePresenter;
import mvp.cn.common.base.MvpView;
import mvp.cn.rx.scheduler.AndroidSchedulerTransformer;
import rx.Observable;
import rx.Subscriber;

/**
 * A presenter for RxJava, that assumes that only one Observable is subscribed by this presenter.
 * The idea is, that you make your (chain of) Observable and pass it to {@link
 * <code>observeOn()</code>
 *
 * @author Summer
 * @since 1.1.0
 */
public abstract class MvpRxPresenter<M extends MvpModel, V extends MvpView>
        extends MvpBasePresenter<V> {

    protected M mvpModel;

    public void setModel(M model) {
        this.mvpModel = model;
    }

    public M getModel() {
        return mvpModel;
    }

    protected Subscriber subscriber;


    /**
     * Subscribes the presenter himself as subscriber on the observable
     *
     * @param observable The observable to subscribe
     */
    public void getNetWork(Observable observable, Subscriber sb) {
        unSubscribe();
        subscriber = sb;
        observable = applyScheduler(observable);
        observable.subscribe(subscriber);
    }

    /**
     * <code>observeOn()</code>. As default it uses {@link AndroidSchedulerTransformer}. Override
     * this
     * method if you want to provide your own scheduling implementation.
     */
    protected <BEAN extends Object> Observable<BEAN> applyScheduler(Observable observable) {
        return observable.compose(new AndroidSchedulerTransformer<BEAN>());
    }


    /**
     * UnSubscribes the subscriber and set it to null
     */
    protected void unSubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
        subscriber = null;
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            unSubscribe();
        }
    }
}