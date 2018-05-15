
package mvp.cn.common.delegate;

import mvp.cn.common.base.MvpPresenter;
import mvp.cn.common.base.MvpView;

/**
 * The default implementation of {@link ViewGroupMvpDelegate}
 *
 * @author Summer
 * @see ViewGroupMvpDelegate
 * @since 1.1.0
 */
public class ViewGroupMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        implements ViewGroupMvpDelegate<V, P> {

    protected MvpDelegateCallback<V, P> delegateCallback;
    protected MvpInternalDelegate<V, P> internalDelegate;

    public ViewGroupMvpDelegateImpl(MvpDelegateCallback<V, P> delegateCallback) {
        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.delegateCallback = delegateCallback;
    }

    protected MvpInternalDelegate<V, P> getInternalDelegate() {
        if (internalDelegate == null) {
            internalDelegate = new MvpInternalDelegate<>(delegateCallback);
        }

        return internalDelegate;
    }

    @Override
    public void onAttachedToWindow() {
        getInternalDelegate().createPresenter();
        getInternalDelegate().attachView();
    }

    @Override
    public void onDetachedFromWindow() {
        getInternalDelegate().detachView();
    }
}
