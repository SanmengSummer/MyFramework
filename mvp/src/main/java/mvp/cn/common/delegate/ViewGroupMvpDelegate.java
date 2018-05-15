
package mvp.cn.common.delegate;

import android.view.View;
import android.widget.FrameLayout;

import mvp.cn.common.base.MvpPresenter;
import mvp.cn.common.base.MvpView;

/**
 * The mvp delegate used for everything that derives from {@link View} like {@link FrameLayout}
 * etc.
 * <p/>
 * <p/>
 * The following methods must be called from the corresponding View lifecycle method:
 * <ul>
 * <li>{@link #onAttachedToWindow()}</li>
 * <li>{@link #onDetachedFromWindow()}</li>
 * </ul>
 *
 * @author Summer
 * @since 1.1.0
 */
public interface ViewGroupMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * Must be called from {@link View#onAttachedToWindow()}
     */
    void onAttachedToWindow();

    /**
     * Must be called from {@link View#onDetachedFromWindow()}
     */
    void onDetachedFromWindow();

}
