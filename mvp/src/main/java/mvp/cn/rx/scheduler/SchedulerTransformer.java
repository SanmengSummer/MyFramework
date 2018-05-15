
package mvp.cn.rx.scheduler;



import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;

/**
 * A {@link Observable.Transformer} that is used to set the schedulers for an Observable that can
 * be subscribed by {@link MvpRxPresenter}.
 *
 * @author Summer
 *
 */
public interface SchedulerTransformer<T> extends Observable.Transformer<T, T> {
}
