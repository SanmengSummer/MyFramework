
package summer.app2;


import dagger.Component;
import summer.BaseFramework.component.AppComponent;

@Component(dependencies = AppComponent.class)
public interface MainComponent {
    MainActivity inject(MainActivity activity);
}