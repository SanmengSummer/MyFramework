
package summer.BaseFramework.base;

/**
*Summer
 */
public interface BaseContract {

    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {
        void complete();

    }
}
