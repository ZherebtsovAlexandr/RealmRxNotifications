package mansonheart.com.realmrxnotifications.di.components;

import dagger.Component;
import mansonheart.com.realmrxnotifications.MainActivity;
import mansonheart.com.realmrxnotifications.di.module.EventsModule;
import mansonheart.com.realmrxnotifications.di.scope.PerActivity;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
@PerActivity
@Component(
        modules = {EventsModule.class}, dependencies = AppComponent.class
)
public interface EventsComponent {
    void inject(MainActivity activity);
}
