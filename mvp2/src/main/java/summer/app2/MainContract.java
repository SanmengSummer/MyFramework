package summer.app2;

import summer.BaseFramework.base.BaseContract;

/**
 * @author Summer.
 * @date 2016/8/3.
 */
public interface MainContract {

    interface View extends BaseContract.BaseView {
        void loginSuccess();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void login();
    }

}
