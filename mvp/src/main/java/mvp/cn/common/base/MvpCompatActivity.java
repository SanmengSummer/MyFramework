
package mvp.cn.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mvp.cn.common.delegate.ActivityMvpDelegate;
import mvp.cn.common.delegate.ActivityMvpDelegateImpl;
import mvp.cn.common.delegate.MvpDelegateCallback;


/**
 * A {@link AppCompatActivity} that uses an {@link MvpPresenter} to implement a Model-View-Presenter
 * Architecture.
 *
 * @author tinggu
 *
 */
public abstract class MvpCompatActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity
        implements MvpDelegateCallback<V, P>, MvpView {

    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    /**
     * Instantiate a presenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    public abstract P createPresenter();

    /**
     * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
     * view from presenter.
     * <p/>
     * <p><b>Please note that only one instance of mvp delegate should be used per Activity
     * instance</b>.
     * </p>
     * <p/>
     * <p>
     * Only override this method if you really know what you are doing.
     * </p>
     *
     * @return {@link ActivityMvpDelegateImpl}
     */
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this);
        }

        return mvpDelegate;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainingInstance() {
        return false;
    }
}
