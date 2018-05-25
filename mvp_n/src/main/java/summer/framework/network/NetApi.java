
package summer.framework.network;

import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import summer.app.SystemParamModel;
import summer.framework.base.Constant;

/**
 *
 * Summer
 */
public class NetApi {

    public static NetApi instance;

    private NetApiService service;

    public NetApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(NetApiService.class);
    }

    public static NetApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new NetApi(okHttpClient);
        return instance;
    }

    public Observable<SystemParamModel> queryParamInfo() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("versionName", "3.9.6.5");
            tempMap.put("versionCode", "39032");
            tempMap.put("channel", "CH");
            tempMap.put("clientCode", "standard");
            tempMap.put("androidModel", TextUtils.isEmpty(Build.MODEL) ? "" : Build.MODEL.replaceAll(" ", ""));
            tempMap.put("androidSdk", Build.VERSION.SDK_INT + "");
            paramsMap.putAll(tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service.queryParamInfo(paramsMap);
    }

}
