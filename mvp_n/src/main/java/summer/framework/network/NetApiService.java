
package summer.framework.network;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import summer.app.SystemParamModel;

/**
 * Summer
 */
public interface NetApiService {
    //注释的使用，可参见https://www.jianshu.com/p/308f3c54abdd
    @FormUrlEncoded
    @POST("system_queryParamInfo.ws?")
    Observable<SystemParamModel> queryParamInfo(@FieldMap Map<String, Object> map);
}