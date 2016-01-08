package mansonheart.com.realmrxnotifications.di.module;

import dagger.Module;
import dagger.Provides;
import mansonheart.com.database.RealmAccessLayer;
import mansonheart.com.realmrxnotifications.di.scope.PerActivity;
import mansonheart.com.realmrxnotifications.interactor.EventProvider;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */

@Module
public class EventsModule {
    @Provides
    @PerActivity
    EventProvider provideEventProvider(RealmAccessLayer realmAccessLayer) {
        return new EventProvider(realmAccessLayer);
    }
}
