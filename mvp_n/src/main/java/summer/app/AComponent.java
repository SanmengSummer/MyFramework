package summer.app;

import dagger.Component;
import summer.app.activity.MainActivity;
import summer.app.fragment.FirstFragment;
import summer.framework.component.AppComponent;

@Component(dependencies = AppComponent.class)
public interface AComponent {
    MainActivity inject(MainActivity activity);
    FirstFragment inject(FirstFragment fragment);
}