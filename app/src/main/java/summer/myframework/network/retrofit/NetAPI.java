package summer.myframework.network.retrofit;

import java.util.Map;

import summer.myframework.activity.SystemParamModel;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Summer on 2017/5/12.
 */

public interface NetAPI {
    @POST(NetUrl.queryParamInfo)
    Observable<SystemParamModel> queryParamInfo(@QueryMap Map<String, Object> map);
}
