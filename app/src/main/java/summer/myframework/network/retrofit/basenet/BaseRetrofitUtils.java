package summer.myframework.network.retrofit.basenet;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import summer.myframework.network.retrofit.NetAPI;


/**
 * Created by Summer on 2017/5/12.
 */

public class BaseRetrofitUtils implements BaseConfig {

    protected static Retrofit retrofit;
    protected static OkHttpClient okHttpClient;
    protected static NetAPI api;
    protected static BaseRetrofitUtils instance;

    protected static BaseRetrofitUtils createApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(NetAPI.class);
        return new BaseRetrofitUtils();
    }

    //指定线程
    protected static Observable<?> getObservable(Observable<?> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.d("retrofit", "retrofitBack = " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS);
            okHttpClient = builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true) // 失败重发
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
        return okHttpClient;
    }

    public static BaseRetrofitUtils getInstance() {
        if (instance == null) {
            createApi();
        }
        return instance;
    }
}
