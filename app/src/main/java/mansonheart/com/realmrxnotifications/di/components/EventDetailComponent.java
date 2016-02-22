package mansonheart.com.realmrxnotifications.di.components;

import dagger.Component;
import mansonheart.com.realmrxnotifications.di.module.EventsModule;
import mansonheart.com.realmrxnotifications.di.scope.PerActivity;
import mansonheart.com.realmrxnotifications.presenter.EventDetailsPresenter;
import mansonheart.com.realmrxnotifications.ui.EventDetailsActivity;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */

@PerActivity
@Component(
        modules = {EventsModule.class}, dependencies = AppComponent.class
)
public interface EventDetailComponent {
    EventDetailsPresenter presenter();
    void inject(EventDetailsActivity activity);
}
