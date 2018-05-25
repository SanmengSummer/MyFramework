package summer.app.activity;

import summer.framework.base.BaseContract;

/**
 * @author Summer.
 * @date 2016/8/3.
 */
public interface MainContract {

    interface View extends BaseContract.BaseView {
        void loginSuccess();

        void showFirst();

        void showSecond();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void login();
    }

}
