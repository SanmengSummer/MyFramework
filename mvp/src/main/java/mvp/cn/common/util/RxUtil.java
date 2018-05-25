
package mvp.cn.common.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.List;

import mvp.cn.common.application.BaseApplication;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable rxCreateDiskObservable(final String key, final Class<T> clazz) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                LogUtil.d("get data from disk: key==" + key);
                String json = ACache.get(BaseApplication.getInstance()).getAsString(key);
                LogUtil.d("get data from disk finish , json==" + json);
                if (!TextUtils.isEmpty(json)) {
                    subscriber.onNext(json);
                }
                subscriber.onCompleted();
            }
        })
                .map(new Func1<String, T>() {
                    @Override
                    public T call(String s) {
                        return new Gson().fromJson(s, clazz);
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public static <T> Observable.Transformer<T, T> rxCacheListHelper(final String key) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())//指定doOnNext执行线程是新线程
                        .doOnNext(new Action1<T>() {
                            @Override
                            public void call(final T data) {
                                Schedulers.io().createWorker().schedule(new Action0() {
                                    @Override
                                    public void call() {
                                        LogUtil.d("get data from network finish ,start cache...");
                                        //通过反射获取List,再判空决定是否缓存
                                        if (data == null)
                                            return;
                                        Class clazz = data.getClass();
                                        Field[] fields = clazz.getFields();
                                        for (Field field : fields) {
                                            String className = field.getType().getSimpleName();
                                            // 得到属性值
                                            if (className.equalsIgnoreCase("List")) {
                                                try {
                                                    List list = (List) field.get(data);
                                                    LogUtil.d("list==" + list);
                                                    if (list != null && !list.isEmpty()) {
                                                        ACache.get(BaseApplication.getInstance())
                                                                .put(key, new Gson().toJson(data, clazz));
                                                        LogUtil.d("cache finish");
                                                    }
                                                } catch (IllegalAccessException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> rxCacheBeanHelper(final String key) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())//指定doOnNext执行线程是新线程
                        .doOnNext(new Action1<T>() {
                            @Override
                            public void call(final T data) {
                                Schedulers.io().createWorker().schedule(new Action0() {
                                    @Override
                                    public void call() {
                                        LogUtil.d("get data from network finish ,start cache...");
                                        ACache.get(BaseApplication.getInstance())
                                                .put(key, new Gson().toJson(data, data.getClass()));
                                        LogUtil.d("cache finish");
                                    }
                                });
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
