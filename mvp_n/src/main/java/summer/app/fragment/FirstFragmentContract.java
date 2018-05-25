package summer.app.fragment;

import summer.framework.base.BaseContract;

/**
 * @author Summer.
 * @date 2016/8/3.
 */
public interface FirstFragmentContract {

    interface View extends BaseContract.BaseView {
        void loginSuccess();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void login();
    }

}
