package summer.myframework.network.retrofit;

import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import summer.myframework.network.retrofit.basenet.BaseRetrofitUtils;


/**
 * Created by Summer on 2017/5/12.
 */

public class RetrofitUtils extends BaseRetrofitUtils {
    public static RetrofitUtils getInstance(){
        return ((RetrofitUtils)BaseRetrofitUtils.getInstance());
    }

    /**
     * queryParamInfo
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
            paramsMap.putAll(tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.queryParamInfo(paramsMap));
    }
}
