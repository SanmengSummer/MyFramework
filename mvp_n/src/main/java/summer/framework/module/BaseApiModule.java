package summer.framework.module;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import summer.framework.network.NetApi;
import summer.framework.network.support.Logger;
import summer.framework.network.support.LoggingInterceptor;

@Module
public class BaseApiModule {
    @Provides
    public OkHttpClient provideOkHttpClient() {
        //日志拦截器，HttpLoggingInterceptor和LoggingInterceptor基本一样，效果也一样
        /*HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.d("retrofit", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/
        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
        logging.setLevel(LoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected NetApi provideBookService(OkHttpClient okHttpClient) {
        return NetApi.getInstance(okHttpClient);
    }
}