
package summer.BaseFramework.module;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import summer.BaseFramework.api.NetApi;
import summer.BaseFramework.api.support.HeaderInterceptor;
import summer.BaseFramework.api.support.Logger;
import summer.BaseFramework.api.support.LoggingInterceptor;
import okhttp3.OkHttpClient;

@Module
public class BaseApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected NetApi provideBookService(OkHttpClient okHttpClient) {
        return NetApi.getInstance(okHttpClient);
    }
}