
package summer.framework.component;

import android.content.Context;

import dagger.Component;
import summer.framework.module.AppModule;
import summer.framework.module.BaseApiModule;
import summer.framework.network.NetApi;

/**
 * Summer
 */
@Component(modules = {AppModule.class, BaseApiModule.class})
public interface AppComponent {
    Context getContext();

    NetApi getReaderApi();
}