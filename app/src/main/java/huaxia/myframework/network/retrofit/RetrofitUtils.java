package huaxia.myframework.network.retrofit;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Summer on 2017/5/12.
 */

public class RetrofitUtils implements BaseConfig {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static NetAPI api;
    private static RetrofitUtils instance;

    private static RetrofitUtils createApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(NetAPI.class);
        return new RetrofitUtils();
    }

    //指定线程
    private static Observable<?> getObservable(Observable<?> observable) {
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
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpClient = builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    connectTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    addInterceptor(loggingInterceptor).build();
        }
        return okHttpClient;
    }

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            createApi();
        }
        return instance;
    }


    //-------------------------------------------请求内容----------------------------------------------

    /**
     * queryParamInfo
     *
     * @return
     */
    public static Observable queryParamInfo() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("versionName", "3.9.6.5");
            tempMap.put("versionCode", "39032");
            tempMap.put("channel", "CH");
            tempMap.put("clientCode", "standard");
            tempMap.put("androidModel", TextUtils.isEmpty(Build.MODEL) ? "" : Build.MODEL.replaceAll(" ", ""));
            tempMap.put("androidSdk", Build.VERSION.SDK_INT + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.queryParamInfo(paramsMap));
    }
}
