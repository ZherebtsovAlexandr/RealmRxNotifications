package mansonheart.com.realmrxnotifications.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mansonheart.com.database.RealmRepository;
import mansonheart.com.realmrxnotifications.di.scope.PerActivity;
import mansonheart.com.realmrxnotifications.interactor.AddEventInteractor;
import mansonheart.com.realmrxnotifications.interactor.GetEventListInteractor;
import mansonheart.com.realmrxnotifications.interactor.UseCase;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */

@Module
public class EventsModule {
    @Provides
    @PerActivity
    @Named("GetEventListInteractor")
    UseCase provideGetEventListInteractor(RealmRepository realmRepository) {
        return new GetEventListInteractor(realmRepository);
    }

    @Provides
    @PerActivity
    @Named("AddEventInteractor")
    UseCase provideAddEventInteractor(RealmRepository realmRepository) {
        return new AddEventInteractor(realmRepository);
    }
}
