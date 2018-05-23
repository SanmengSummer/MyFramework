package summer.myframework.network.retrofit;

/**
*  创建人 huaxia
*/
public interface BaseConfig {
    //----------------BaseUrl-----------------------
    //    String API_BASE_URL = "http://qs.tayaapp.cn";//正式服务器//
    String API_BASE_URL = "http://lexueinterface.huan.tv/";//测试服务器

    //---------------OkHttp配置-----------------------
    long HTTP_CONNECT_TIMEOUT = 1000 * 30;

    long HTTP_READ_TIMEOUT = HTTP_CONNECT_TIMEOUT;
}
