
package summer.BaseFramework.component;

import android.content.Context;

import dagger.Component;
import summer.BaseFramework.api.NetApi;
import summer.BaseFramework.module.AppModule;
import summer.BaseFramework.module.BaseApiModule;

/**
 * Summer
 */
@Component(modules = {AppModule.class, BaseApiModule.class})
public interface AppComponent {

    Context getContext();

    NetApi getReaderApi();

}