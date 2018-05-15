
package mvp.cn.common.delegate;

import mvp.cn.common.base.MvpPresenter;
import mvp.cn.common.base.MvpView;

/**
 * This is just the internal implementation for the delegate. Don't use it by your own.
 *
 * @param <V> The type of {@link MvpView}
 * @param <P> The type of {@link MvpPresenter}
 * @author Summer
 * @since 1.1.0
 */
class MvpInternalDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    protected MvpDelegateCallback<V, P> delegateCallback;

    MvpInternalDelegate(MvpDelegateCallback<V, P> delegateCallback) {

        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }

        this.delegateCallback = delegateCallback;
    }

    /**
     * Called  to create the presenter (if no other one already exisits)
     */
    void createPresenter() {
        P presenter = delegateCallback.getPresenter();
        if (presenter == null) {
            presenter = delegateCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }

        delegateCallback.setPresenter(presenter);
    }

    /**
     * Attaches the view to the presenter
     */
    void attachView() {
        getPresenter().attachView(delegateCallback.getMvpView());
    }

    /**
     * Called to detach the view from presenter
     */
    void detachView() {
        getPresenter().detachView(delegateCallback.isRetainingInstance());
    }

    private P getPresenter() {
        P presenter = delegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter returned from getPresenter() is null");
        }
        return presenter;
    }
}
