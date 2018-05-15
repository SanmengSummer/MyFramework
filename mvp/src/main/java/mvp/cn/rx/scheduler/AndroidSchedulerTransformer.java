package mvp.cn.rx.scheduler;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Summer
 *
 */
public class AndroidSchedulerTransformer<T> implements SchedulerTransformer<T> {

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
