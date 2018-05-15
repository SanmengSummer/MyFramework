package huaxia.myframework.network.retrofit;

import java.util.Map;

import huaxia.myframework.activity.SystemParamModel;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Summer on 2017/5/12.
 */

public interface NetAPI {
    @FormUrlEncoded
    @POST(NetUrl.queryParamInfo)
    Observable<SystemParamModel> queryParamInfo(@FieldMap Map<String, Object> map);
}
