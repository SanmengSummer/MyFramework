
package summer.BaseFramework.api;

import java.util.Map;

import summer.app2.SystemParamModel;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * https://github.com/JustWayward/BookReader
 *
 * Summer
 */
public interface NetApiService {
    @FormUrlEncoded
    @POST("system_queryParamInfo.ws?")
    Observable<SystemParamModel> queryParamInfo(@FieldMap Map<String, Object> map);
}